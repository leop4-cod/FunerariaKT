package com.funeraria.app.ui.viewmodel;

import com.funeraria.app.data.local.TokenDataStore;
import com.funeraria.app.domain.repository.AuthRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class AuthViewModel_Factory implements Factory<AuthViewModel> {
  private final Provider<AuthRepository> authRepositoryProvider;

  private final Provider<TokenDataStore> tokenDataStoreProvider;

  public AuthViewModel_Factory(Provider<AuthRepository> authRepositoryProvider,
      Provider<TokenDataStore> tokenDataStoreProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.tokenDataStoreProvider = tokenDataStoreProvider;
  }

  @Override
  public AuthViewModel get() {
    return newInstance(authRepositoryProvider.get(), tokenDataStoreProvider.get());
  }

  public static AuthViewModel_Factory create(Provider<AuthRepository> authRepositoryProvider,
      Provider<TokenDataStore> tokenDataStoreProvider) {
    return new AuthViewModel_Factory(authRepositoryProvider, tokenDataStoreProvider);
  }

  public static AuthViewModel newInstance(AuthRepository authRepository,
      TokenDataStore tokenDataStore) {
    return new AuthViewModel(authRepository, tokenDataStore);
  }
}
