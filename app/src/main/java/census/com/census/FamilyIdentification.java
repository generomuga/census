package census.com.census;

import com.google.firebase.database.ServerValue;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class FamilyIdentification {

    private String id;
    private String fName;
    private String mName;
    private String lName;
    private String houseNp;
    private String streetNo;
    private String barangay;
    private String municipality;
    private String province;
    private String region;
    private int residency;
    private int ownership;
    private int familyStatus;
    private String user;

    public FamilyIdentification() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getHouseNp() {
        return houseNp;
    }

    public void setHouseNp(String houseNp) {
        this.houseNp = houseNp;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getResidency() {
        return residency;
    }

    public void setResidency(int residency) {
        this.residency = residency;
    }

    public int getOwnership() {
        return ownership;
    }

    public void setOwnership(int ownership) {
        this.ownership = ownership;
    }

    public int getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(int familyStatus) {
        this.familyStatus = familyStatus;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
