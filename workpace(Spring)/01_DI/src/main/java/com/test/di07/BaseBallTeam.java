package com.test.di07;

public class BaseBallTeam {
	private String manger;						// 감독
	private String battingCoach;			// 타격코치
	private String pitcherCoach;			// 투수코치
	private String hitter;							// 타자
	private String pitcher;							// 투수
	
	public BaseBallTeam() {}
	
	public BaseBallTeam(String manager, String battingCoach,
			String pticherCoach) {
		this.manger = manager;
		this.battingCoach = battingCoach;
		this.pitcherCoach = pticherCoach;
		
	} // 인자 생성자

	public String getManger() {
		return manger;
	}

	public void setManger(String manger) {
		this.manger = manger;
	}

	public String getBattingCoach() {
		return battingCoach;
	}

	public void setBattingCoach(String battingCoach) {
		this.battingCoach = battingCoach;
	}

	public String getPitcherCoach() {
		return pitcherCoach;
	}

	public void setPitcherCoach(String pitcherCoach) {
		this.pitcherCoach = pitcherCoach;
	}

	public String getHitter() {
		return hitter;
	}

	public void setHitter(String hitter) {
		this.hitter = hitter;
	}

	public String getPitcher() {
		return pitcher;
	}

	public void setPitcher(String pitcher) {
		this.pitcher = pitcher;
	}
	
	
								
							
}
