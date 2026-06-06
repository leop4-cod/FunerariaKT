package com.funeraria.app.data.repository;

import com.funeraria.app.data.remote.api.ClienteApi;
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
public final class ClienteRepositoryImpl_Factory implements Factory<ClienteRepositoryImpl> {
  private final Provider<ClienteApi> apiProvider;

  public ClienteRepositoryImpl_Factory(Provider<ClienteApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public ClienteRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static ClienteRepositoryImpl_Factory create(Provider<ClienteApi> apiProvider) {
    return new ClienteRepositoryImpl_Factory(apiProvider);
  }

  public static ClienteRepositoryImpl newInstance(ClienteApi api) {
    return new ClienteRepositoryImpl(api);
  }
}
