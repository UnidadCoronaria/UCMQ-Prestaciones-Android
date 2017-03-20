package com.unidadcoronaria.domain.usecase;

import android.content.Context;

import com.unidadcoronaria.domain.BusProvider;
import com.unidadcoronaria.domain.model.Medicament;
import com.unidadcoronaria.domain.transformer.MedicamentTransformer;
import com.unidadcoronaria.domain.transformer.MedicamentTransformer;
import com.unidadcoronaria.prestaciones.data.entity.MedicamentEntity;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class GetMedicamentUseCase extends UseCase<List<Medicament>> {

    private final MedicamentTransformer transformer = new MedicamentTransformer();

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().getMedicament(new SuccessFailureCallBack<List<MedicamentEntity>>() {
            @Override
            public void onSuccess(List<MedicamentEntity> list) {
                BusProvider.getDefaultBus().post(new SuccessResponse(transformer.transform(list)));
            }

            @Override
            public void onFailure(String message) {
                BusProvider.getDefaultBus().post(new ErrorResponse());
            }
        });
    }

    //region Inner Classes
    public static class SuccessResponse {

        private List<Medicament> Medicament;

        public SuccessResponse(List<Medicament> Medicament) {
            this.Medicament = Medicament;
        }

        public List<Medicament> getMedicament() {
            return Medicament;
        }
    }

    public static class ErrorResponse {

    }
    //endregion
}
