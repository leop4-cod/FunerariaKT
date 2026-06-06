package com.funeraria.app.data.repository;

import com.funeraria.app.data.remote.api.EmpleadoApi;
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
public final class EmpleadoRepositoryImpl_Factory implements Factory<EmpleadoRepositoryImpl> {
  private final Provider<EmpleadoApi> apiProvider;

  public EmpleadoRepositoryImpl_Factory(Provider<EmpleadoApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public EmpleadoRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static EmpleadoRepositoryImpl_Factory create(Provider<EmpleadoApi> apiProvider) {
    return new EmpleadoRepositoryImpl_Factory(apiProvider);
  }

  public static EmpleadoRepositoryImpl newInstance(EmpleadoApi api) {
    return new EmpleadoRepositoryImpl(api);
  }
}
