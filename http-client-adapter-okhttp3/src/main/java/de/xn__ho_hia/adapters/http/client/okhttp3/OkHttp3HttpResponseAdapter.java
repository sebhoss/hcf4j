package de.xn__ho_hia.adapters.http.client.okhttp3;

import java.io.IOException;

import de.xn__ho_hia.adapters.http.client.HttpResponse;
import de.xn__ho_hia.adapters.http.client.exception.HttpResponseException;
import okhttp3.Response;

final class OkHttp3HttpResponseAdapter implements HttpResponse {

    private final Response response;

    OkHttp3HttpResponseAdapter(final Response response) {
        this.response = response;
    }

    @Override
    public String getBodyContent() {
        try {
            return response.body().string();
        } catch (final IOException exception) {
            throw new HttpResponseException(exception);
        }
    }

}