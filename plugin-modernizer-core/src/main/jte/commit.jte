@import io.jenkins.tools.pluginmodernizer.core.model.Plugin
@import io.jenkins.tools.pluginmodernizer.core.model.Recipe
@import static io.jenkins.tools.pluginmodernizer.core.config.Settings.RECIPE_FQDN_PREFIX
@param Plugin plugin
@param Recipe recipe
Applied recipe ${recipe.getName().replaceAll(RECIPE_FQDN_PREFIX + ".", "")}