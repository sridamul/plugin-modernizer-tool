package io.jenkins.tools.pluginmodernizer.core.recipes;

import io.jenkins.tools.pluginmodernizer.core.extractor.PluginMetadata;
import io.jenkins.tools.pluginmodernizer.core.extractor.PomResolutionVisitor;
import org.openrewrite.ExecutionContext;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.marker.SearchResult;
import org.openrewrite.maven.MavenIsoVisitor;
import org.openrewrite.xml.tree.Xml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Determines if this project is using a BOM in it's bom file
 */
public class IsMissingBom extends Recipe {

    /**
     * LOGGER.
     */
    private static final Logger LOG = LoggerFactory.getLogger(IsMissingBom.class);

    @Override
    public String getDisplayName() {
        return "Is the project missing a Jenkins bom";
    }

    @Override
    public String getDescription() {
        return "Checks if the project is missing a Jenkins BOM.";
    }

    @Override
    public TreeVisitor<?, ExecutionContext> getVisitor() {
        return new MavenIsoVisitor<>() {
            @Override
            public Xml.Document visitDocument(Xml.Document document, ExecutionContext ctx) {

                PluginMetadata pluginMetadata = new PomResolutionVisitor().reduce(document, new PluginMetadata());

                if (pluginMetadata.getBomVersion() == null) {
                    LOG.info("Project is missing Jenkins BOM");
                    return SearchResult.found(document, "Project is missing Jenkins bom");
                }

                return document;
            }
        };
    }
}
