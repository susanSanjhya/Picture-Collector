# Load and display images from the internet

## 1. Add repository and Manual Dependency Injection

### Separate UI Layer and Data Layer

- Data Layer
  Responsible for the business logic and for sourcing and saving data.
  Exposes data to the UI layer using `Unidirectional Data Flow` pattern.

#### Creating data layer

##### Create Repository

```kotlin
interface PhotoRepository {
  suspend fun getPhotos(): List<Photo>
}

class DefaultPhotoRepository: PhotoRepository {
  override suspend fun getPhotos(): List<Photo> {
    return PhotoApi.retrofitService.getPhotos()
  }
}
```
