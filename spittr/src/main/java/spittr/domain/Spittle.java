package spittr.domain;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Spittle {

	private final Long id;

	@NotNull
	@Size(min = 10, max = 140)
	private final String message;

	@NotNull
	private final Date time;

	@NotNull
	private Double latitude;

	@NotNull
	private Double longitude;

	@NotNull
	private Long spitter;

	


	public Spittle(String message, Date time, Long spitter) {
		this(null, message, time, null, null, spitter);
	}




	public Spittle(Long id, String message, Date time, Double longitude, Double latitude, Long spitter) {
		this.id = id;
		this.message = message;
		this.time = time;
		this.longitude = longitude;
		this.latitude = latitude;
		this.spitter = spitter;
	}
	
	
	

	public long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public Date getTime() {
		return time;
	}

	public Double getLongitude() {
		return longitude;
	}

	public Double getLatitude() {
		return latitude;
	}


	public Long getSpitter() {
		return spitter;
	}

	public void setSpitter(Long spitter) {
		this.spitter = spitter;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "id", "time");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "id", "time");
	}

	@Override
	public String toString() {
		return "Spittle [id=" + id + ", message=" + message + ", time=" + time + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", spitter=" + spitter + "]";
	}

}
