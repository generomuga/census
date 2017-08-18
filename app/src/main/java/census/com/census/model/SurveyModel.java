package census.com.census.model;

public interface SurveyModel {
    interface OnFamilyIdentification{
        interface OnResult{
            void setErrorDataFamilyIdentification(String message);
            void onSuccessFamilyIdentification();
        }
        void sendFamilyIdentificationData(String fName,String mName,String lName,String region,String province,
                      String municipality,String barangay,String houseNo,String streetNo, int residency, int ownership, int status, String user);
    }

    interface OnFamily{
        interface OnResult{
            void setErrorFamilyData(String message);
            void onSuccessFamily();
        }
        void sendData(int familyNo, int yearReside, String region, String province, String municipality, String barangay, String isp, int bicycle, int qBicycle,
                      int boat, int qBoat, int bus, int qBus, int car, int qCar, int jeep, int qJeep, int motorboat, int qMotorboat, int motorcycle, int qMotorcyle,
                      int owner, int qOwner, int pedicab, int qPedicab, int pickup, int qPickup, int pumpboat, int qPumpboat, int raft, int qRaft, int suv, int qSuv,
                      int tric, int qTric, int truck, int qTruck, int van, int qVan);
    }

}
