package uz.mrsolijon.quizapp.ui.game

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.mrsolijon.quizapp.R
import uz.mrsolijon.quizapp.databinding.FragmentDialogGameOverBinding
import uz.mrsolijon.quizapp.utils.Constants


class GameOverFragmentDialog : DialogFragment(R.layout.fragment_dialog_game_over) {

    private var _binding: FragmentDialogGameOverBinding? = null

    private val binding get() = _binding!!

    private val args: GameOverFragmentDialogArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return Dialog(requireContext(), R.style.Theme_Material_Light_Dialog_NoMinWidth)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDialogGameOverBinding.bind(view)

        binding.btnBackHome.setOnClickListener {
            dismiss()
            findNavController().navigate(R.id.action_gameOverFragmentDialog_to_homeFragment)
        }

        binding.btnReplay.setOnClickListener {
            dismiss()
            findNavController().navigate(
                R.id.gameFragment,
                arguments,
                NavOptions.Builder().setPopUpTo(R.id.gameFragment, true).build()
            )
        }

        configureUi()

    }

    private fun configureUi() {
        val data = args.Game
        binding.apply {
            tvScoreInfo.text = getString(
                R.string.text_score_info,
                data.correctAnswersCount,
                Constants.QUESTIONS_AMOUNT
            )
            tvCorrectCount.text =
                getString(R.string.text_correct, data.correctAnswersCount)
            tvIncorrectCount.text =
                getString(R.string.text_incorrect, data.incorrectAnswersCount)
            tvCategory.text = getString(R.string.text_category, data.category)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}