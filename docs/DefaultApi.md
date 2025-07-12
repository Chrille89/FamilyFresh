# DefaultApi

All URIs are relative to *http://localhost:8080*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**createRecipes**](DefaultApi.md#createRecipes) | **POST** /recipes | Create recipes. |
| [**deleteRecipeById**](DefaultApi.md#deleteRecipeById) | **DELETE** /recipes/{id} | Delete a specific recipe by id. |
| [**getActualMenu**](DefaultApi.md#getActualMenu) | **GET** /recipes/menu | Return the actual menu |
| [**getAllRecipes**](DefaultApi.md#getAllRecipes) | **GET** /recipes | Return all recipes. |
| [**getRecipeById**](DefaultApi.md#getRecipeById) | **GET** /recipes/{id} | Return a specific recipe by id. |
| [**updateMenu**](DefaultApi.md#updateMenu) | **PUT** /recipes/menu | Update the actual menu |


<a id="createRecipes"></a>
# **createRecipes**
> kotlin.collections.List&lt;RecipeReadDto&gt; createRecipes(recipeWriteDto)

Create recipes.

Create recipes.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val recipeWriteDto : kotlin.collections.List<RecipeWriteDto> =  // kotlin.collections.List<RecipeWriteDto> | Recipes in JSON array format.
try {
    val result : kotlin.collections.List<RecipeReadDto> = apiInstance.createRecipes(recipeWriteDto)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#createRecipes")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#createRecipes")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **recipeWriteDto** | [**kotlin.collections.List&lt;RecipeWriteDto&gt;**](RecipeWriteDto.md)| Recipes in JSON array format. | |

### Return type

[**kotlin.collections.List&lt;RecipeReadDto&gt;**](RecipeReadDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="deleteRecipeById"></a>
# **deleteRecipeById**
> RecipeReadDto deleteRecipeById(id)

Delete a specific recipe by id.

Delete a specific recipe by id.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val id : kotlin.String = id_example // kotlin.String | The Id of the recipe
try {
    val result : RecipeReadDto = apiInstance.deleteRecipeById(id)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#deleteRecipeById")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#deleteRecipeById")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **id** | **kotlin.String**| The Id of the recipe | |

### Return type

[**RecipeReadDto**](RecipeReadDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="getActualMenu"></a>
# **getActualMenu**
> kotlin.collections.List&lt;RecipeReadDto&gt; getActualMenu(random)

Return the actual menu

Return the actual menu

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val random : kotlin.Boolean = true // kotlin.Boolean | Get a random menu or the actual menu
try {
    val result : kotlin.collections.List<RecipeReadDto> = apiInstance.getActualMenu(random)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#getActualMenu")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#getActualMenu")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **random** | **kotlin.Boolean**| Get a random menu or the actual menu | [optional] [default to false] |

### Return type

[**kotlin.collections.List&lt;RecipeReadDto&gt;**](RecipeReadDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="getAllRecipes"></a>
# **getAllRecipes**
> kotlin.collections.List&lt;RecipeReadDto&gt; getAllRecipes()

Return all recipes.

Return all recipes.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
try {
    val result : kotlin.collections.List<RecipeReadDto> = apiInstance.getAllRecipes()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#getAllRecipes")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#getAllRecipes")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.collections.List&lt;RecipeReadDto&gt;**](RecipeReadDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="getRecipeById"></a>
# **getRecipeById**
> RecipeReadDto getRecipeById(id)

Return a specific recipe by id.

Return a specific recipe by id.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val id : kotlin.String = id_example // kotlin.String | The Id of the recipe
try {
    val result : RecipeReadDto = apiInstance.getRecipeById(id)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#getRecipeById")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#getRecipeById")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **id** | **kotlin.String**| The Id of the recipe | |

### Return type

[**RecipeReadDto**](RecipeReadDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="updateMenu"></a>
# **updateMenu**
> kotlin.collections.List&lt;RecipeReadDto&gt; updateMenu(recipeReadDto)

Update the actual menu

Update the actual menu

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = DefaultApi()
val recipeReadDto : kotlin.collections.List<RecipeReadDto> =  // kotlin.collections.List<RecipeReadDto> | Recipes for the new menu
try {
    val result : kotlin.collections.List<RecipeReadDto> = apiInstance.updateMenu(recipeReadDto)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling DefaultApi#updateMenu")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling DefaultApi#updateMenu")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **recipeReadDto** | [**kotlin.collections.List&lt;RecipeReadDto&gt;**](RecipeReadDto.md)| Recipes for the new menu | |

### Return type

[**kotlin.collections.List&lt;RecipeReadDto&gt;**](RecipeReadDto.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

