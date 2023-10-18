package com.example.fileupload.model.voterlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "sharedetails")
public class ShareDetails {
    private String shareName;
    private String shareHolder;
    private int quantity;

    private float shareCost;

    private float profit;

    public ShareDetails(String shareName, String shareHolder, int quantity, float shareCost, float profit) {
        this.profit=profit;
        this.shareCost=shareCost;
        this.shareHolder=shareHolder;
        this.shareName=shareName;
        this.quantity=quantity;
    }
}
