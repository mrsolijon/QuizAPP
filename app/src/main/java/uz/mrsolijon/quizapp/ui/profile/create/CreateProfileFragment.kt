package uz.mrsolijon.quizapp.ui.profile.create

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import uz.mrsolijon.quizapp.data.local.entity.UserEntity
import uz.mrsolijon.quizapp.viewmodels.ProfileViewModel
import uz.mrsolijon.quizapp.utils.Constants
import uz.mrsolijon.quizapp.utils.Constants.PREFS_IS_HAVE
import uz.mrsolijon.quizapp.utils.Constants.makeDateToString
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.text.TextUtils
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import uz.mrsolijon.quizapp.BuildConfig
import uz.mrsolijon.quizapp.R
import uz.mrsolijon.quizapp.databinding.FragmentCreateProfileBinding
import java.util.Calendar
import javax.inject.Inject


@AndroidEntryPoint
class CreateProfileFragment : Fragment(R.layout.fragment_create_profile) {

    private var _binding: FragmentCreateProfileBinding? = null
    private val binding get() = _binding!!

    private var imageUri: Uri? = null

    private val viewModel: ProfileViewModel by viewModels()

    private lateinit var datePickerDialog: DatePickerDialog

    @Inject
    lateinit var prefs: SharedPreferences

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCreateProfileBinding.bind(view)

        configureDatePickerDialog()

        binding.edtDateBirth.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                datePickerDialog.show()
            }
        }

        binding.btnCreate.setOnClickListener {
            saveUserWithScoreDataToDatabase()
            prefs.edit().putBoolean(PREFS_IS_HAVE, true).apply()
        }

        binding.ivSubtract.setOnClickListener {
            checkReadExternalStorage()
        }
    }

    private val selectImageFromGallery = registerForActivityResult(
        StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val data = it.data
            imageUri = data?.data
            if (imageUri != null) {
                Glide.with(binding.root).load(imageUri).into(binding.ivUserImage)
            } else {
                binding.ivUserImage.setImageResource(R.drawable.placeholder)
            }
        }
    }

    private fun saveUserWithScoreDataToDatabase() {
        val firstname = binding.edtFname.text.toString()
        val email = binding.edtEmail.text.toString()
        val lastname = binding.edtLname.text.toString()
        val dateOfBirth = binding.edtDateBirth.text.toString()

        if (TextUtils.isEmpty(firstname) || TextUtils.isEmpty(email) || TextUtils.isEmpty(lastname) || TextUtils.isEmpty(
                dateOfBirth
            )
        ) {
            Snackbar.make(binding.root, "Please, fill in all fields", Snackbar.LENGTH_SHORT).show()
        } else {
            val user = UserEntity(
                fName = firstname,
                userImageUri = if (imageUri != null) imageUri.toString() else "",
                lName = lastname,
                email = email,
                dateOfBirth = dateOfBirth
            )
            viewModel.insertUserData(user)
            prefs.edit().putString(Constants.PREFS_USER_NAME, user.fName).apply()
            Snackbar.make(binding.root, "Added your data", Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_createProfileFragment_to_homeFragment)
        }
    }

    private fun configureDatePickerDialog() {
        val onDateSetListener = DatePickerDialog.OnDateSetListener { view, y, m, d ->
            binding.edtDateBirth.setText(
                makeDateToString(
                    day = d, month = m + 1, year = y, requireContext()
                )
            )
        }
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        datePickerDialog = DatePickerDialog(requireContext(), onDateSetListener, year, month, day)
    }

    private fun checkReadExternalStorage() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        when {
            ContextCompat.checkSelfPermission(
                requireContext(), permission
            ) == PackageManager.PERMISSION_GRANTED -> {
                val img = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                selectImageFromGallery.launch(img)
            }

            shouldShowRequestPermissionRationale(permission) -> {
                Snackbar.make(
                    binding.root,
                    getString(R.string.storage_permission_required),
                    Snackbar.LENGTH_SHORT
                ).setAction(getString(R.string.go_to_settings)) {
                    val uri: Uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null)
                    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        data = uri
                        startActivity(this)
                    }
                }.show()
            }

            else -> {
                requestPermissionLauncher.launch(permission)
            }
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                val img = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                selectImageFromGallery.launch(img)
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        imageUri = null
    }
}