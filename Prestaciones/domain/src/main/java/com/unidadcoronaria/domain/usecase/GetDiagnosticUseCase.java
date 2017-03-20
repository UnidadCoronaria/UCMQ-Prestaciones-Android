package com.unidadcoronaria.domain.usecase;

import android.content.Context;

import com.unidadcoronaria.domain.BusProvider;
import com.unidadcoronaria.domain.model.Diagnostic;
import com.unidadcoronaria.domain.transformer.DiagnosticTransformer;
import com.unidadcoronaria.prestaciones.data.entity.DiagnosticEntity;
import com.unidadcoronaria.prestaciones.data.network.ApiClient;
import com.unidadcoronaria.prestaciones.data.network.callback.SuccessFailureCallBack;

import java.util.List;

/**
 * @author Agustin.Bala
 * @since 0.0.1
 */
public class GetDiagnosticUseCase extends UseCase<List<Diagnostic>> {

    private final DiagnosticTransformer transformer = new DiagnosticTransformer();

    @Override
    public void execute(Context aContext) {
        ApiClient.getInstance().getDiagnostic(new SuccessFailureCallBack<List<DiagnosticEntity>>() {
            @Override
            public void onSuccess(List<DiagnosticEntity> list) {
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

        private List<Diagnostic> diagnostics;

        public SuccessResponse(List<Diagnostic> diagnostics) {
            this.diagnostics = diagnostics;
        }

        public List<Diagnostic> getDiagnostics() {
            return diagnostics;
        }
    }

    public static class ErrorResponse {

    }
    //endregion
}
