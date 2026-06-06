package com.funeraria.app.di;

import com.funeraria.app.data.remote.api.EmpleadoApi;
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
public final class NetworkModule_ProvideEmpleadoApiFactory implements Factory<EmpleadoApi> {
  private final Provider<Retrofit> rProvider;

  public NetworkModule_ProvideEmpleadoApiFactory(Provider<Retrofit> rProvider) {
    this.rProvider = rProvider;
  }

  @Override
  public EmpleadoApi get() {
    return provideEmpleadoApi(rProvider.get());
  }

  public static NetworkModule_ProvideEmpleadoApiFactory create(Provider<Retrofit> rProvider) {
    return new NetworkModule_ProvideEmpleadoApiFactory(rProvider);
  }

  public static EmpleadoApi provideEmpleadoApi(Retrofit r) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideEmpleadoApi(r));
  }
}
