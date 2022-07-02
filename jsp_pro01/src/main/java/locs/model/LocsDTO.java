package locs.model;

public class LocsDTO {
	private int localId;
	private String streetAddr;
	private String postCode;
	private String city;
	private String statePro;
	private String countryId;
	public int getLocalID() {
		return localId;
	}
	public void setLocalID(int localId) {
		this.localId = localId;
	}
	public String getStreetAddr() {
		return streetAddr;
	}
	public void setStreetAddr(String streetAddr) {
		this.streetAddr = streetAddr;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStatePro() {
		return statePro;
	}
	public void setStatePro(String statePro) {
		this.statePro = statePro;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	@Override
	public String toString() {
		return "LocsDTO [localId=" + localId + ", streetAddr=" + streetAddr + ", postCode=" + postCode + ", city="
				+ city + ", statePro=" + statePro + ", countryId=" + countryId + "]";
	}
	
	
}
