package com.funeraria.app.di;

import com.funeraria.app.data.remote.api.PagoApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class NetworkModule_ProvidePagoApiFactory implements Factory<PagoApi> {
  private final Provider<Retrofit> rProvider;

  public NetworkModule_ProvidePagoApiFactory(Provider<Retrofit> rProvider) {
    this.rProvider = rProvider;
  }

  @Override
  public PagoApi get() {
    return providePagoApi(rProvider.get());
  }

  public static NetworkModule_ProvidePagoApiFactory create(Provider<Retrofit> rProvider) {
    return new NetworkModule_ProvidePagoApiFactory(rProvider);
  }

  public static PagoApi providePagoApi(Retrofit r) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.providePagoApi(r));
  }
}
