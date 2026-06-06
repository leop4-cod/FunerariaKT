package com.funeraria.app.data.repository;

import com.funeraria.app.data.remote.api.ProveedorApi;
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
public final class ProveedorRepositoryImpl_Factory implements Factory<ProveedorRepositoryImpl> {
  private final Provider<ProveedorApi> apiProvider;

  public ProveedorRepositoryImpl_Factory(Provider<ProveedorApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public ProveedorRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static ProveedorRepositoryImpl_Factory create(Provider<ProveedorApi> apiProvider) {
    return new ProveedorRepositoryImpl_Factory(apiProvider);
  }

  public static ProveedorRepositoryImpl newInstance(ProveedorApi api) {
    return new ProveedorRepositoryImpl(api);
  }
}
