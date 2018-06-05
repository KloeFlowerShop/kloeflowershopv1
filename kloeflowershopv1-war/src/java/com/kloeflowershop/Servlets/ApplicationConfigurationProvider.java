/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kloeflowershop.Servlets;

import javax.servlet.ServletContext;
import org.ocpsoft.logging.Logger.Level;
import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.config.Log;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;

/**
 *
 * @author User
 */
@RewriteConfiguration
public class ApplicationConfigurationProvider extends HttpConfigurationProvider
{
   @Override
   public Configuration getConfiguration(ServletContext context)
   {
      return ConfigurationBuilder.begin()

         .addRule()
         .perform(Log.message(Level.INFO, "Rewrite is active."))
              
         //Logs an inbound request to the server
         .addRule()
         .when(Direction.isInbound().and(Path.matches("/{path}")))
         .perform(Log.message(Level.INFO, "Client requested path: {path}"))
         .where("path").matches(".*")
              
         //Forwards are purely internal operations and the client browser URL will not be updated
         .addRule()
         .when(Direction.isInbound().and(Path.matches("/{path}")))
         .perform(Log.message(Level.INFO, "Client requested path: {path}").and(Forward.to("/KLOEFlorist/{path}.html")))
         .where("path").matches("home")

         // redirect to another page
         .addRule()
         .when(Direction.isInbound().and(Path.matches("/")))
         .perform(Redirect.temporary(context.getContextPath() + "/home"))

         // redirect legacy URLs to a new location
         //.addRule()
         //.when(Direction.isInbound()
         //   .and(Path.matches("/book.php").and(Query.parameterExists("isbn"))))
         //.perform(Redirect.temporary(context.getContextPath() + "/book/{isbn}"))

          // Join a URL to an internal resource
         //.addRule(Join.path("/year/{year}").to("/search/year.jsp"))

          // Join a URL to an internal resource that accepts a parameter
         //.addRule(Join.path("/book/{isbn}").to("/store/book.jsp"))
      ;
   }

   @Override
   public int priority()
   {
      return 0;
   }
}