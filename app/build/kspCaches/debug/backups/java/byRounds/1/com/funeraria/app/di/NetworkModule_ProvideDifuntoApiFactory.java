package com.funeraria.app.di;

import com.funeraria.app.data.remote.api.DifuntoApi;
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
public final class NetworkModule_ProvideDifuntoApiFactory implements Factory<DifuntoApi> {
  private final Provider<Retrofit> rProvider;

  public NetworkModule_ProvideDifuntoApiFactory(Provider<Retrofit> rProvider) {
    this.rProvider = rProvider;
  }

  @Override
  public DifuntoApi get() {
    return provideDifuntoApi(rProvider.get());
  }

  public static NetworkModule_ProvideDifuntoApiFactory create(Provider<Retrofit> rProvider) {
    return new NetworkModule_ProvideDifuntoApiFactory(rProvider);
  }

  public static DifuntoApi provideDifuntoApi(Retrofit r) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideDifuntoApi(r));
  }
}
