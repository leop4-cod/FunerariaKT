package com.funeraria.app.di;

import com.funeraria.app.data.remote.api.ClienteApi;
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
public final class NetworkModule_ProvideClienteApiFactory implements Factory<ClienteApi> {
  private final Provider<Retrofit> rProvider;

  public NetworkModule_ProvideClienteApiFactory(Provider<Retrofit> rProvider) {
    this.rProvider = rProvider;
  }

  @Override
  public ClienteApi get() {
    return provideClienteApi(rProvider.get());
  }

  public static NetworkModule_ProvideClienteApiFactory create(Provider<Retrofit> rProvider) {
    return new NetworkModule_ProvideClienteApiFactory(rProvider);
  }

  public static ClienteApi provideClienteApi(Retrofit r) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideClienteApi(r));
  }
}
