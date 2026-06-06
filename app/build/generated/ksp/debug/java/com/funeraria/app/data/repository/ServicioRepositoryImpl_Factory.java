package com.funeraria.app.data.repository;

import com.funeraria.app.data.remote.api.ServicioApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class ServicioRepositoryImpl_Factory implements Factory<ServicioRepositoryImpl> {
  private final Provider<ServicioApi> apiProvider;

  public ServicioRepositoryImpl_Factory(Provider<ServicioApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public ServicioRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static ServicioRepositoryImpl_Factory create(Provider<ServicioApi> apiProvider) {
    return new ServicioRepositoryImpl_Factory(apiProvider);
  }

  public static ServicioRepositoryImpl newInstance(ServicioApi api) {
    return new ServicioRepositoryImpl(api);
  }
}
