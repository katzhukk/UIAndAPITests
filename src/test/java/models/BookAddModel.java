package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookAddModel {
    private String userId;
    private List<IsbnBookModel> collectionOfIsbns;
}
