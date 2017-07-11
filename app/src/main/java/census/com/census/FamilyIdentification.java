package census.com.census;

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
    private int residency;
    private int ownership;
    private int familyStatus;

    public FamilyIdentification(String id, String fName, String mName, String lName, String houseNp, String streetNo, String barangay, String municipality, String province, int residency, int ownership, int familyStatus) {
        this.id = id;
        this.fName = fName;
        this.mName = mName;
        this.lName = lName;
        this.houseNp = houseNp;
        this.streetNo = streetNo;
        this.barangay = barangay;
        this.municipality = municipality;
        this.province = province;
        this.residency = residency;
        this.ownership = ownership;
        this.familyStatus = familyStatus;
    }

    public String getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getmName() {
        return mName;
    }

    public String getlName() {
        return lName;
    }

    public String getHouseNp() {
        return houseNp;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public String getBarangay() {
        return barangay;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getProvince() {
        return province;
    }

    public int getResidency() {
        return residency;
    }

    public int getOwnership() {
        return ownership;
    }

    public int getFamilyStatus() {
        return familyStatus;
    }
}
