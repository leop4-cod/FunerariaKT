package com.funeraria.app.di;

import com.funeraria.app.data.remote.api.ContratoApi;
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
public final class NetworkModule_ProvideContratoApiFactory implements Factory<ContratoApi> {
  private final Provider<Retrofit> rProvider;

  public NetworkModule_ProvideContratoApiFactory(Provider<Retrofit> rProvider) {
    this.rProvider = rProvider;
  }

  @Override
  public ContratoApi get() {
    return provideContratoApi(rProvider.get());
  }

  public static NetworkModule_ProvideContratoApiFactory create(Provider<Retrofit> rProvider) {
    return new NetworkModule_ProvideContratoApiFactory(rProvider);
  }

  public static ContratoApi provideContratoApi(Retrofit r) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideContratoApi(r));
  }
}
