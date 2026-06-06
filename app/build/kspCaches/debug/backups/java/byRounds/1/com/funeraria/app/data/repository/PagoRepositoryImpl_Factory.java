package com.funeraria.app.data.repository;

import com.funeraria.app.data.remote.api.PagoApi;
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
public final class PagoRepositoryImpl_Factory implements Factory<PagoRepositoryImpl> {
  private final Provider<PagoApi> apiProvider;

  public PagoRepositoryImpl_Factory(Provider<PagoApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public PagoRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static PagoRepositoryImpl_Factory create(Provider<PagoApi> apiProvider) {
    return new PagoRepositoryImpl_Factory(apiProvider);
  }

  public static PagoRepositoryImpl newInstance(PagoApi api) {
    return new PagoRepositoryImpl(api);
  }
}
