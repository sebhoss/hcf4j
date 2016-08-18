package de.xn__ho_hia.adapters.http.client.okhttp3;

import java.io.IOException;
import java.util.function.Function;

import ch.qos.cal10n.IMessageConveyor;
import de.xn__ho_hia.adapters.http.client.HttpResponse;
import de.xn__ho_hia.adapters.http.client.builder.HttpGetRequestBuilder;
import de.xn__ho_hia.adapters.http.client.errors.ConnectionErrors;
import de.xn__ho_hia.adapters.http.client.errors.ThirdPartyErrors;
import de.xn__ho_hia.adapters.http.client.exception.HttpRequestException;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

final class OkHttp3HttpRequestAdapter extends AbstractOkHttp3Adapter implements HttpGetRequestBuilder {

    OkHttp3HttpRequestAdapter(
            final AbstractOkHttp3Adapter adapter,
            final Builder requestBuilder) {
        this(adapter.getClient(), adapter.getMediaTypeCreator(), adapter.getMessages(), requestBuilder);
    }

    OkHttp3HttpRequestAdapter(
            final OkHttpClient client,
            final Function<String, MediaType> mediaTypeCreator,
            final IMessageConveyor messages,
            final Builder requestBuilder) {
        super(client, mediaTypeCreator, messages, requestBuilder);
    }

    @Override
    public HttpResponse executeOnCallingThread() {
        try {
            final Request request = requestBuilder.build();
            final Call call = client.newCall(request);
            final Response response = call.execute();
            return new OkHttp3HttpResponseAdapter(response, messages);
        } catch (final IOException exception) {
            throw new HttpRequestException(messages.getMessage(ConnectionErrors.UNABLE_TO_CONNECT), exception);
        } catch (final IllegalStateException exception) {
            throw new HttpRequestException(messages.getMessage(ThirdPartyErrors.ALREADY_EXECUTED), exception);
        }
    }

}
