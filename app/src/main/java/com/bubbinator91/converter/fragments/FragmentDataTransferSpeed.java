package com.bubbinator91.converter.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bubbinator91.converter.R;

public class FragmentDataTransferSpeed extends Fragment {
	private final boolean DEBUG = true;
	private final String TAG = "FragmentDataTransferSpeed";

	private EditText editTextBps, editTextByps, editTextKbps, editTextKbyps, editTextMbps,
			editTextMbyps, editTextGbps, editTextGbyps, editTextTbps, editTextTbyps;

	private TextWatcher textWatcherBps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
			editTextBps.removeTextChangedListener(textWatcherBps);
			editTextByps.removeTextChangedListener(textWatcherByps);
			editTextKbps.removeTextChangedListener(textWatcherKbps);
			editTextKbyps.removeTextChangedListener(textWatcherKbyps);
			editTextMbps.removeTextChangedListener(textWatcherMbps);
			editTextMbyps.removeTextChangedListener(textWatcherMbyps);
			editTextGbps.removeTextChangedListener(textWatcherGbps);
			editTextGbyps.removeTextChangedListener(textWatcherGbyps);
			editTextTbps.removeTextChangedListener(textWatcherTbps);
			editTextTbyps.removeTextChangedListener(textWatcherTbyps);

			// conversion code here

			editTextBps.addTextChangedListener(textWatcherBps);
			editTextByps.addTextChangedListener(textWatcherByps);
			editTextKbps.addTextChangedListener(textWatcherKbps);
			editTextKbyps.addTextChangedListener(textWatcherKbyps);
			editTextMbps.addTextChangedListener(textWatcherMbps);
			editTextMbyps.addTextChangedListener(textWatcherMbyps);
			editTextGbps.addTextChangedListener(textWatcherGbps);
			editTextGbyps.addTextChangedListener(textWatcherGbyps);
			editTextTbps.addTextChangedListener(textWatcherTbps);
			editTextTbyps.addTextChangedListener(textWatcherTbyps);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherByps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
			editTextBps.removeTextChangedListener(textWatcherBps);
			editTextByps.removeTextChangedListener(textWatcherByps);
			editTextKbps.removeTextChangedListener(textWatcherKbps);
			editTextKbyps.removeTextChangedListener(textWatcherKbyps);
			editTextMbps.removeTextChangedListener(textWatcherMbps);
			editTextMbyps.removeTextChangedListener(textWatcherMbyps);
			editTextGbps.removeTextChangedListener(textWatcherGbps);
			editTextGbyps.removeTextChangedListener(textWatcherGbyps);
			editTextTbps.removeTextChangedListener(textWatcherTbps);
			editTextTbyps.removeTextChangedListener(textWatcherTbyps);

			// conversion code here

			editTextBps.addTextChangedListener(textWatcherBps);
			editTextByps.addTextChangedListener(textWatcherByps);
			editTextKbps.addTextChangedListener(textWatcherKbps);
			editTextKbyps.addTextChangedListener(textWatcherKbyps);
			editTextMbps.addTextChangedListener(textWatcherMbps);
			editTextMbyps.addTextChangedListener(textWatcherMbyps);
			editTextGbps.addTextChangedListener(textWatcherGbps);
			editTextGbyps.addTextChangedListener(textWatcherGbyps);
			editTextTbps.addTextChangedListener(textWatcherTbps);
			editTextTbyps.addTextChangedListener(textWatcherTbyps);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherKbps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
			editTextBps.removeTextChangedListener(textWatcherBps);
			editTextByps.removeTextChangedListener(textWatcherByps);
			editTextKbps.removeTextChangedListener(textWatcherKbps);
			editTextKbyps.removeTextChangedListener(textWatcherKbyps);
			editTextMbps.removeTextChangedListener(textWatcherMbps);
			editTextMbyps.removeTextChangedListener(textWatcherMbyps);
			editTextGbps.removeTextChangedListener(textWatcherGbps);
			editTextGbyps.removeTextChangedListener(textWatcherGbyps);
			editTextTbps.removeTextChangedListener(textWatcherTbps);
			editTextTbyps.removeTextChangedListener(textWatcherTbyps);

			// conversion code here

			editTextBps.addTextChangedListener(textWatcherBps);
			editTextByps.addTextChangedListener(textWatcherByps);
			editTextKbps.addTextChangedListener(textWatcherKbps);
			editTextKbyps.addTextChangedListener(textWatcherKbyps);
			editTextMbps.addTextChangedListener(textWatcherMbps);
			editTextMbyps.addTextChangedListener(textWatcherMbyps);
			editTextGbps.addTextChangedListener(textWatcherGbps);
			editTextGbyps.addTextChangedListener(textWatcherGbyps);
			editTextTbps.addTextChangedListener(textWatcherTbps);
			editTextTbyps.addTextChangedListener(textWatcherTbyps);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherKbyps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
			editTextBps.removeTextChangedListener(textWatcherBps);
			editTextByps.removeTextChangedListener(textWatcherByps);
			editTextKbps.removeTextChangedListener(textWatcherKbps);
			editTextKbyps.removeTextChangedListener(textWatcherKbyps);
			editTextMbps.removeTextChangedListener(textWatcherMbps);
			editTextMbyps.removeTextChangedListener(textWatcherMbyps);
			editTextGbps.removeTextChangedListener(textWatcherGbps);
			editTextGbyps.removeTextChangedListener(textWatcherGbyps);
			editTextTbps.removeTextChangedListener(textWatcherTbps);
			editTextTbyps.removeTextChangedListener(textWatcherTbyps);

			// conversion code here

			editTextBps.addTextChangedListener(textWatcherBps);
			editTextByps.addTextChangedListener(textWatcherByps);
			editTextKbps.addTextChangedListener(textWatcherKbps);
			editTextKbyps.addTextChangedListener(textWatcherKbyps);
			editTextMbps.addTextChangedListener(textWatcherMbps);
			editTextMbyps.addTextChangedListener(textWatcherMbyps);
			editTextGbps.addTextChangedListener(textWatcherGbps);
			editTextGbyps.addTextChangedListener(textWatcherGbyps);
			editTextTbps.addTextChangedListener(textWatcherTbps);
			editTextTbyps.addTextChangedListener(textWatcherTbyps);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherMbps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
			editTextBps.removeTextChangedListener(textWatcherBps);
			editTextByps.removeTextChangedListener(textWatcherByps);
			editTextKbps.removeTextChangedListener(textWatcherKbps);
			editTextKbyps.removeTextChangedListener(textWatcherKbyps);
			editTextMbps.removeTextChangedListener(textWatcherMbps);
			editTextMbyps.removeTextChangedListener(textWatcherMbyps);
			editTextGbps.removeTextChangedListener(textWatcherGbps);
			editTextGbyps.removeTextChangedListener(textWatcherGbyps);
			editTextTbps.removeTextChangedListener(textWatcherTbps);
			editTextTbyps.removeTextChangedListener(textWatcherTbyps);

			// conversion code here

			editTextBps.addTextChangedListener(textWatcherBps);
			editTextByps.addTextChangedListener(textWatcherByps);
			editTextKbps.addTextChangedListener(textWatcherKbps);
			editTextKbyps.addTextChangedListener(textWatcherKbyps);
			editTextMbps.addTextChangedListener(textWatcherMbps);
			editTextMbyps.addTextChangedListener(textWatcherMbyps);
			editTextGbps.addTextChangedListener(textWatcherGbps);
			editTextGbyps.addTextChangedListener(textWatcherGbyps);
			editTextTbps.addTextChangedListener(textWatcherTbps);
			editTextTbyps.addTextChangedListener(textWatcherTbyps);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherMbyps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
			editTextBps.removeTextChangedListener(textWatcherBps);
			editTextByps.removeTextChangedListener(textWatcherByps);
			editTextKbps.removeTextChangedListener(textWatcherKbps);
			editTextKbyps.removeTextChangedListener(textWatcherKbyps);
			editTextMbps.removeTextChangedListener(textWatcherMbps);
			editTextMbyps.removeTextChangedListener(textWatcherMbyps);
			editTextGbps.removeTextChangedListener(textWatcherGbps);
			editTextGbyps.removeTextChangedListener(textWatcherGbyps);
			editTextTbps.removeTextChangedListener(textWatcherTbps);
			editTextTbyps.removeTextChangedListener(textWatcherTbyps);

			// conversion code here

			editTextBps.addTextChangedListener(textWatcherBps);
			editTextByps.addTextChangedListener(textWatcherByps);
			editTextKbps.addTextChangedListener(textWatcherKbps);
			editTextKbyps.addTextChangedListener(textWatcherKbyps);
			editTextMbps.addTextChangedListener(textWatcherMbps);
			editTextMbyps.addTextChangedListener(textWatcherMbyps);
			editTextGbps.addTextChangedListener(textWatcherGbps);
			editTextGbyps.addTextChangedListener(textWatcherGbyps);
			editTextTbps.addTextChangedListener(textWatcherTbps);
			editTextTbyps.addTextChangedListener(textWatcherTbyps);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherGbps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
			editTextBps.removeTextChangedListener(textWatcherBps);
			editTextByps.removeTextChangedListener(textWatcherByps);
			editTextKbps.removeTextChangedListener(textWatcherKbps);
			editTextKbyps.removeTextChangedListener(textWatcherKbyps);
			editTextMbps.removeTextChangedListener(textWatcherMbps);
			editTextMbyps.removeTextChangedListener(textWatcherMbyps);
			editTextGbps.removeTextChangedListener(textWatcherGbps);
			editTextGbyps.removeTextChangedListener(textWatcherGbyps);
			editTextTbps.removeTextChangedListener(textWatcherTbps);
			editTextTbyps.removeTextChangedListener(textWatcherTbyps);

			// conversion code here

			editTextBps.addTextChangedListener(textWatcherBps);
			editTextByps.addTextChangedListener(textWatcherByps);
			editTextKbps.addTextChangedListener(textWatcherKbps);
			editTextKbyps.addTextChangedListener(textWatcherKbyps);
			editTextMbps.addTextChangedListener(textWatcherMbps);
			editTextMbyps.addTextChangedListener(textWatcherMbyps);
			editTextGbps.addTextChangedListener(textWatcherGbps);
			editTextGbyps.addTextChangedListener(textWatcherGbyps);
			editTextTbps.addTextChangedListener(textWatcherTbps);
			editTextTbyps.addTextChangedListener(textWatcherTbyps);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherGbyps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
			editTextBps.removeTextChangedListener(textWatcherBps);
			editTextByps.removeTextChangedListener(textWatcherByps);
			editTextKbps.removeTextChangedListener(textWatcherKbps);
			editTextKbyps.removeTextChangedListener(textWatcherKbyps);
			editTextMbps.removeTextChangedListener(textWatcherMbps);
			editTextMbyps.removeTextChangedListener(textWatcherMbyps);
			editTextGbps.removeTextChangedListener(textWatcherGbps);
			editTextGbyps.removeTextChangedListener(textWatcherGbyps);
			editTextTbps.removeTextChangedListener(textWatcherTbps);
			editTextTbyps.removeTextChangedListener(textWatcherTbyps);

			// conversion code here

			editTextBps.addTextChangedListener(textWatcherBps);
			editTextByps.addTextChangedListener(textWatcherByps);
			editTextKbps.addTextChangedListener(textWatcherKbps);
			editTextKbyps.addTextChangedListener(textWatcherKbyps);
			editTextMbps.addTextChangedListener(textWatcherMbps);
			editTextMbyps.addTextChangedListener(textWatcherMbyps);
			editTextGbps.addTextChangedListener(textWatcherGbps);
			editTextGbyps.addTextChangedListener(textWatcherGbyps);
			editTextTbps.addTextChangedListener(textWatcherTbps);
			editTextTbyps.addTextChangedListener(textWatcherTbyps);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherTbps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
			editTextBps.removeTextChangedListener(textWatcherBps);
			editTextByps.removeTextChangedListener(textWatcherByps);
			editTextKbps.removeTextChangedListener(textWatcherKbps);
			editTextKbyps.removeTextChangedListener(textWatcherKbyps);
			editTextMbps.removeTextChangedListener(textWatcherMbps);
			editTextMbyps.removeTextChangedListener(textWatcherMbyps);
			editTextGbps.removeTextChangedListener(textWatcherGbps);
			editTextGbyps.removeTextChangedListener(textWatcherGbyps);
			editTextTbps.removeTextChangedListener(textWatcherTbps);
			editTextTbyps.removeTextChangedListener(textWatcherTbyps);

			// conversion code here

			editTextBps.addTextChangedListener(textWatcherBps);
			editTextByps.addTextChangedListener(textWatcherByps);
			editTextKbps.addTextChangedListener(textWatcherKbps);
			editTextKbyps.addTextChangedListener(textWatcherKbyps);
			editTextMbps.addTextChangedListener(textWatcherMbps);
			editTextMbyps.addTextChangedListener(textWatcherMbyps);
			editTextGbps.addTextChangedListener(textWatcherGbps);
			editTextGbyps.addTextChangedListener(textWatcherGbyps);
			editTextTbps.addTextChangedListener(textWatcherTbps);
			editTextTbyps.addTextChangedListener(textWatcherTbyps);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

	private TextWatcher textWatcherTbyps = new TextWatcher() {
		@Override
		public void afterTextChanged(Editable s) {
			editTextBps.removeTextChangedListener(textWatcherBps);
			editTextByps.removeTextChangedListener(textWatcherByps);
			editTextKbps.removeTextChangedListener(textWatcherKbps);
			editTextKbyps.removeTextChangedListener(textWatcherKbyps);
			editTextMbps.removeTextChangedListener(textWatcherMbps);
			editTextMbyps.removeTextChangedListener(textWatcherMbyps);
			editTextGbps.removeTextChangedListener(textWatcherGbps);
			editTextGbyps.removeTextChangedListener(textWatcherGbyps);
			editTextTbps.removeTextChangedListener(textWatcherTbps);
			editTextTbyps.removeTextChangedListener(textWatcherTbyps);

			// conversion code here

			editTextBps.addTextChangedListener(textWatcherBps);
			editTextByps.addTextChangedListener(textWatcherByps);
			editTextKbps.addTextChangedListener(textWatcherKbps);
			editTextKbyps.addTextChangedListener(textWatcherKbyps);
			editTextMbps.addTextChangedListener(textWatcherMbps);
			editTextMbyps.addTextChangedListener(textWatcherMbyps);
			editTextGbps.addTextChangedListener(textWatcherGbps);
			editTextGbyps.addTextChangedListener(textWatcherGbyps);
			editTextTbps.addTextChangedListener(textWatcherTbps);
			editTextTbyps.addTextChangedListener(textWatcherTbyps);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {}
	};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (DEBUG)
			Log.d(TAG, "Entered onCreateView");

		View rootView = inflater.inflate(R.layout.fragment_data_transfer_speed, container, false);

		editTextBps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_bps));
		editTextByps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_byps));
		editTextKbps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_kbps));
		editTextKbyps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_kbyps));
		editTextMbps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_mbps));
		editTextMbyps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_mbyps));
		editTextGbps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_gbps));
		editTextGbyps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_gbyps));
		editTextTbps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_tbps));
		editTextTbyps = ((EditText) rootView.findViewById(R.id.editText_data_transfer_speed_tbyps));

		editTextBps.addTextChangedListener(textWatcherBps);
		editTextByps.addTextChangedListener(textWatcherByps);
		editTextKbps.addTextChangedListener(textWatcherKbps);
		editTextKbyps.addTextChangedListener(textWatcherKbyps);
		editTextMbps.addTextChangedListener(textWatcherMbps);
		editTextMbyps.addTextChangedListener(textWatcherMbyps);
		editTextGbps.addTextChangedListener(textWatcherGbps);
		editTextGbyps.addTextChangedListener(textWatcherGbyps);
		editTextTbps.addTextChangedListener(textWatcherTbps);
		editTextTbyps.addTextChangedListener(textWatcherTbyps);

        return rootView;
    }
}
