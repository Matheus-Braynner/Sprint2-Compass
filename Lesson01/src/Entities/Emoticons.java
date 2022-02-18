package Entities;

public class Emoticons {
	private String msg;

	public Emoticons() {
	}

	public Emoticons(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public String checkEmoticons(String ans) {
		int replaceHappy = ans.length() - ans.replace(":-)", "").length();
		int replaceSad = ans.length() - ans.replace(":-(", "").length();
		String status = null;
		if (replaceHappy == 0 && replaceSad == 0) {
			throw new RuntimeException("Enter emoticons in your setence");
		} else if (replaceHappy > replaceSad) {
			return status = "Happy";
		} else if (replaceSad > replaceHappy) {
			return status = "Sad";
		} else if (replaceHappy == replaceSad){
			return status = "Neutral";
		}
		return status;
	}

}
