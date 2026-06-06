package com.funeraria.app.data.repository;

import com.funeraria.app.data.remote.api.ContratoApi;
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
public final class ContratoRepositoryImpl_Factory implements Factory<ContratoRepositoryImpl> {
  private final Provider<ContratoApi> apiProvider;

  public ContratoRepositoryImpl_Factory(Provider<ContratoApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public ContratoRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static ContratoRepositoryImpl_Factory create(Provider<ContratoApi> apiProvider) {
    return new ContratoRepositoryImpl_Factory(apiProvider);
  }

  public static ContratoRepositoryImpl newInstance(ContratoApi api) {
    return new ContratoRepositoryImpl(api);
  }
}
