package app.ericdock.iupui.edu.nflteams;

/**
 * Created by ericd on 2/6/2017.
 */

public class NFLTeam {
    public String mTeamName;
    public String mTeamShortName;
    public String mLogoImage;
    public String mConference;
    public String mDivision;
    public String mStadium;
    public String mLatitude;
    public String mLongitude;

    NFLTeam (String teamName, String teamShortName,
              String logoImage, String conference,
              String division, String stadium,
              String latitude, String longitude) {
        mTeamName = teamName;
        mTeamShortName = teamShortName;
        mLogoImage = logoImage;
        mConference = conference;
        mDivision = division;
        mStadium = stadium;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    public String getTeamName() {
        return mTeamName;
    }

    public void setTeamName(String teamName) {
        mTeamName = teamName;
    }

    public String getTeamShortName() {
        return mTeamShortName;
    }

    public void setTeamShortName(String teamShortName) {
        mTeamShortName = teamShortName;
    }

    public String getLogoImage() {
        return mLogoImage;
    }

    public void setLogoImage(String logoImage) {
        mLogoImage = logoImage;
    }

    public String getConference() {
        return mConference;
    }

    public void setConference(String conference) {
        mConference = conference;
    }

    public String getDivision() {
        return mDivision;
    }

    public void setDivision(String division) {
        mDivision = division;
    }

    public String getStadium() {
        return mStadium;
    }

    public void setStadium(String stadium) {
        mStadium = stadium;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String latitude) {
        mLatitude = latitude;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String longitude) {
        mLongitude = longitude;
    }
}
