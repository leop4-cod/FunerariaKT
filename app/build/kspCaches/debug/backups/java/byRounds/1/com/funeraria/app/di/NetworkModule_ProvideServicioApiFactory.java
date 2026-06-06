package com.funeraria.app.di;

import com.funeraria.app.data.remote.api.ServicioApi;
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
public final class NetworkModule_ProvideServicioApiFactory implements Factory<ServicioApi> {
  private final Provider<Retrofit> rProvider;

  public NetworkModule_ProvideServicioApiFactory(Provider<Retrofit> rProvider) {
    this.rProvider = rProvider;
  }

  @Override
  public ServicioApi get() {
    return provideServicioApi(rProvider.get());
  }

  public static NetworkModule_ProvideServicioApiFactory create(Provider<Retrofit> rProvider) {
    return new NetworkModule_ProvideServicioApiFactory(rProvider);
  }

  public static ServicioApi provideServicioApi(Retrofit r) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideServicioApi(r));
  }
}
