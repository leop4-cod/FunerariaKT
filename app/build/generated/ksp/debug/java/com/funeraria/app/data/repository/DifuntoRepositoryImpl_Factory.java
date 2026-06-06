package com.funeraria.app.data.repository;

import com.funeraria.app.data.remote.api.DifuntoApi;
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
public final class DifuntoRepositoryImpl_Factory implements Factory<DifuntoRepositoryImpl> {
  private final Provider<DifuntoApi> apiProvider;

  public DifuntoRepositoryImpl_Factory(Provider<DifuntoApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public DifuntoRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static DifuntoRepositoryImpl_Factory create(Provider<DifuntoApi> apiProvider) {
    return new DifuntoRepositoryImpl_Factory(apiProvider);
  }

  public static DifuntoRepositoryImpl newInstance(DifuntoApi api) {
    return new DifuntoRepositoryImpl(api);
  }
}
