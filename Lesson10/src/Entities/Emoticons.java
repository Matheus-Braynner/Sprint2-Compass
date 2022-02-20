package Entities;

import db.DbException;

public class Emoticons {
	private String name;
	private String msg;

	public Emoticons() {
	}

	public Emoticons(String msg, String name) {
		this.msg = msg;
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}
	
	public String getName() {
		return name;
	}

	public String checkEmoticons(String ans) {
		int replaceHappy = ans.length() - ans.replace(":-)", "").length();
		int replaceSad = ans.length() - ans.replace(":-(", "").length();
		String status = null;
		if (replaceHappy == 0 && replaceSad == 0) {
			throw new DbException("Enter emoticons in your setence");
		} else if (replaceHappy > replaceSad) {
			return status = "Happy";
		} else if (replaceSad > replaceHappy) {
			return status = "Sad";
		} else if (replaceHappy == replaceSad){
			return status = "Neutral";
		}
		return status;
	}
	
	public Boolean therIsNoEmote(String ans) {
		int replaceHappy = ans.length() - ans.replace(":-)", "").length();
		int replaceSad = ans.length() - ans.replace(":-(", "").length();
		if (replaceHappy == 0 && replaceSad == 0) {
			throw new DbException("Enter emoticons in your setence");
		}  else {
			return false;
		}
		

	}

}
