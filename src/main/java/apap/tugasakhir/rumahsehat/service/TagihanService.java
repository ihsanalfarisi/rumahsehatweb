package apap.tugasakhir.rumahsehat.service;

import apap.tugasakhir.rumahsehat.model.AppointmentModel;
import apap.tugasakhir.rumahsehat.model.ObatModel;
import apap.tugasakhir.rumahsehat.model.ResepModel;
import apap.tugasakhir.rumahsehat.model.TagihanModel;

import java.util.List;

public interface TagihanService {
    public int getHarga(ResepModel resep);
    public void addTagihanResep(TagihanModel tagihan, AppointmentModel appointment);
    public TagihanModel getTagihanByAppointment(AppointmentModel appointment);
    public List<TagihanModel> getListTagihanByIdObat(String idObat);
}
