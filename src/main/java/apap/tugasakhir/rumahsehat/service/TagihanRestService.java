package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.TagihanModel;

import java.util.List;

public interface TagihanRestService {
    List<TagihanModel> retrieveListTagihan(String username);
}
