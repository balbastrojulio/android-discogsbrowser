package work.beltran.discogsbrowser.api.model.record;

import com.google.gson.annotations.SerializedName;

/**
 * Created by miquel on 22.04.16.
 */
public class Record {
    @SerializedName("id")
    private String instance_id;

    @SerializedName("basic_information")
    private BasicInformation basicInformation;

    public BasicInformation getBasicInformation() {
        return basicInformation;
    }

    public void setBasicInformation(BasicInformation basicInformation) {
        this.basicInformation = basicInformation;
    }

    public String getInstance_id() {
        return instance_id;
    }

    public void setInstance_id(String instance_id) {
        this.instance_id = instance_id;
    }
}
