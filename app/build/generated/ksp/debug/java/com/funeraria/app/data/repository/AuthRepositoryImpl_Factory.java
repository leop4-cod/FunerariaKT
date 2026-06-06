package com.funeraria.app.data.repository;

import com.funeraria.app.data.local.TokenDataStore;
import com.funeraria.app.data.remote.api.AuthApi;
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
public final class AuthRepositoryImpl_Factory implements Factory<AuthRepositoryImpl> {
  private final Provider<AuthApi> apiProvider;

  private final Provider<TokenDataStore> tokenDataStoreProvider;

  public AuthRepositoryImpl_Factory(Provider<AuthApi> apiProvider,
      Provider<TokenDataStore> tokenDataStoreProvider) {
    this.apiProvider = apiProvider;
    this.tokenDataStoreProvider = tokenDataStoreProvider;
  }

  @Override
  public AuthRepositoryImpl get() {
    return newInstance(apiProvider.get(), tokenDataStoreProvider.get());
  }

  public static AuthRepositoryImpl_Factory create(Provider<AuthApi> apiProvider,
      Provider<TokenDataStore> tokenDataStoreProvider) {
    return new AuthRepositoryImpl_Factory(apiProvider, tokenDataStoreProvider);
  }

  public static AuthRepositoryImpl newInstance(AuthApi api, TokenDataStore tokenDataStore) {
    return new AuthRepositoryImpl(api, tokenDataStore);
  }
}
