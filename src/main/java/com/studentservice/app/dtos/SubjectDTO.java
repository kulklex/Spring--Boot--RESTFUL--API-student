package com.studentservice.app.dtos;

import javax.validation.constraints.NotNull;

public class SubjectDTO {
	
	
		@NotNull
		private String mathematics;
		private String science;
		private String english;
		private String tech;
		private String civic;
		private String economics;
		
		
		public String getMathematics() {
			return mathematics;
		}
		public void setMathematics(String mathematics) {
			this.mathematics = mathematics;
		}
		public String getScience() {
			return science;
		}
		public void setScience(String science) {
			this.science = science;
		}
		public String getEnglish() {
			return english;
		}
		public void setEnglish(String english) {
			this.english = english;
		}
		public String getTech() {
			return tech;
		}
		public void setTech(String tech) {
			this.tech = tech;
		}
		public String getCivic() {
			return civic;
		}
		public void setCivic(String civic) {
			this.civic = civic;
		}
		public String getEconomics() {
			return economics;
		}
		public void setEconomics(String economics) {
			this.economics = economics;
		}
		
		
		public SubjectDTO(String mathematics, String science, String english, String tech, String civic, String economics) {
			super();
			this.mathematics = mathematics;
			this.science = science;
			this.english = english;
			this.tech = tech;
			this.civic = civic;
			this.economics = economics;
		}
		
		
		public SubjectDTO() {
		}
		
		
		@Override
		public String toString() {
			return "Subject [mathematics=" + mathematics + ", science=" + science + ", english=" + english + ", tech="
					+ tech + ", civic=" + civic + ", economics=" + economics + "]";
		}
		


}
