package com.example.yaison.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
	
	private EditText etIdCard;
	
	private String buildFor(String numbers) {
		
		if (numbers.length() <= 3) {
			return numbers;
		}
		
		StringBuilder sb = new StringBuilder();
		
		String xxx = numbers.substring(0, 3);
		sb.append(xxx);
		sb.append("-");
		int end = (numbers.length() <= 10) ? numbers.length() : 10;
		String xxxxxxx = numbers.substring(3, end);
		sb.append(xxxxxxx);
		
		if (numbers.length() <= 10) {
			String ans = sb.toString();
			
			return ans;
		}
		
		String x = numbers.substring(10, 11);
		sb.append("-");
		sb.append(x);
		
		String ans = sb.toString();
		
		return ans;
	}
	
	private String extractNum(CharSequence txt) {
		StringBuilder buff = new StringBuilder();
		
		for (int i = 0; i < txt.length(); i++) {
			char c = txt.charAt(i);
			if (isNum(c)) {
				buff.append(c);
			}
		}
		
		String ans = buff.toString();
		
		return ans;
	}
	
	private static boolean isNum(char c) {
		if (c >= '0' && c <= '9') {
			return true;
		}
		
		return false;
	}
	
	private static int diff(String a, String b) {
		if (a == null && b == null) {
			return -1;
		}
		
		//one is null, but not the other
		if (a == null || b == null) {
			return 0;
		}
		
		for (int i = 0; i < a.length() || i < b.length(); i++) {
			char x;
			char y;
			
			if (i < a.length()) {
				x = a.charAt(i);
			} else {
				return i;
			}
			
			if (i < b.length()) {
				y = b.charAt(i);
			} else {
				return i;
			}
			
			if (x != y) {
				return i;
			}
			
		}
		
		return -1;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etIdCard = findViewById(R.id.idCard);
		
		etIdCard.addTextChangedListener(new TextWatcher() {
			
			private final ThreadLocal<String> threadLocalValue = new ThreadLocal<String>() {
				@Override
				protected String initialValue() {
					return null;
				}
			};
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			
			}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
				final String newValue = threadLocalValue.get();
				final String crt = s.toString();
				
				if (crt.equals(newValue)) {
					return;
				}
				
				String nums = extractNum(crt);
				String withFormat = buildFor(nums);
				
				threadLocalValue.set(withFormat);
				
				
				int start = etIdCard.getSelectionStart();
				int end = etIdCard.getSelectionEnd();
				s.replace(0, s.length(), withFormat, 0, withFormat.length());
				
				if(start == end){
					//case no ranged selection
					if(start < 3){
						Selection.setSelection(s, start);
					}
				}
				
			}
		});
	}
	
}
