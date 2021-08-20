package com.vincent.recipe_mvvm_jetpack.presentation.ui.recipe_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.vincent.recipe_mvvm_jetpack.presentation.BaseApplication
import com.vincent.recipe_mvvm_jetpack.presentation.components.*
import com.vincent.recipe_mvvm_jetpack.presentation.components.util.SnackbarController
import com.vincent.recipe_mvvm_jetpack.presentation.ui.recipe_list.RecipeListEvent.*
import com.vincent.recipe_mvvm_jetpack.presentation.theme.AppTheme
import com.vincent.recipe_mvvm_jetpack.util.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val snackbarController = SnackbarController(lifecycleScope)

    private val viewModel: RecipeListViewModel by viewModels()

    @ExperimentalComposeUiApi
    @ExperimentalUnitApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val snackbarHostState = remember { SnackbarHostState() }

                Column {
                    Button(onClick = {
                        // The job is tied to the lifecycle of the fragment
                        // When the fragment is destroyed, the snackbar is also destroyed
                        lifecycleScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Hey look a snackbar",
                                actionLabel = "Hide",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }) {
                        Text(text = "Show Snackbar")
                    }
                }

                val recipes = viewModel.recipes.value

                // Observing data from viewModel requested from Api
                for (recipe in recipes) {
                    Log.d(TAG, "onCreateView: ${recipe.title}")
                }

                // Composable functions can store a single object in memory by using the remember composable.
                // A value computed by remember is stored in the Composition during initial composition,
                // and the stored value is returned during recomposition.
                // remember can be used to store both mutable and immutable objects.
                // https://developer.android.com/jetpack/compose/state
                // But this way is not perfect because the value will lose during the screen rotation
                // val query = remember { mutableStateOf("Beef") }

                // Better to define query in the viewModel
                val query = viewModel.query.value

                val selectedCategory = viewModel.selectedCategory.value

                val loading = viewModel.loading.value

                val page = viewModel.page.value

                val scaffoldState = rememberScaffoldState()


                AppTheme(
                    darkTheme = application.isDark.value,
                    displayProgressBar = loading,
                    scaffoldState = scaffoldState
                ) {
                    Scaffold(
                        topBar = {
                            // This is the pattern you should remember for the text capturing
                            SearchAppBar(
                                query = query,
                                onQueryChanged = viewModel::onQueryChanged,
                                onExecuteSearch = {
                                    if (viewModel.selectedCategory.value?.value == "Milk") {
                                        snackbarController.getScope().launch {
                                            snackbarController.showSnackbar(
                                                message = "Invalid category: Milk",
                                                actionLabel = "Hide",
                                                scaffoldState = scaffoldState
                                            )
                                        }
                                    } else {
                                        viewModel.onTriggerEvent(NewSearchEvent)
                                    }
                                },
                                scrollPosition = viewModel.categoryScrollPosition,
                                selectedCategory = selectedCategory,
                                onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                                onChangeCategoryScrollPosition = viewModel::onChangeCategoryScrollPosition,
                                onToggleTheme = {
                                    application.toggleLightTheme()
                                }
                            )
                        },
                        bottomBar = { MyBottomBar() },
                        scaffoldState = scaffoldState,
                        snackbarHost = {
                            scaffoldState.snackbarHostState
                        }
                    ) {
                        RecipeList(
                            loading = loading,
                            recipes = recipes,
                            onChangeRecipeScrollPosition = viewModel::onChangeRecipeScrollPosition,
                            page = page,
                            onNextPage = { viewModel.onTriggerEvent(NextPageEvent) },
                            scaffoldState = scaffoldState,
                            snackbarController = snackbarController,
                            navController = findNavController()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MyBottomBar() {
    BottomNavigation(elevation = 12.dp) {
        BottomNavigationItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = { Icon(Icons.Default.Search, "bottomSearch") })
        BottomNavigationItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = { Icon(Icons.Default.BrokenImage, "bottomBrokenImage") })
        BottomNavigationItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = { Icon(Icons.Default.AccountBalance, "bottomAccountBalance") })
    }
}
