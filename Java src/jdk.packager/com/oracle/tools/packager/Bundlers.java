/*
 * Copyright (c) 2014, 2017, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.oracle.tools.packager;

import java.util.Collection;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @deprecated use {@link ToolProvider} to locate the {@code "javapackager"} tool instead.
 */
@Deprecated(since="10", forRemoval=true)
public interface Bundlers {

    /**
     * This convenience method will call {@link #createBundlersInstance(ClassLoader)}
     * with the classloader that this Bundlers is loaded from.
     *
     * @return an instance of Bundlers loaded and configured from the current ClassLoader.
     */
    public static Bundlers createBundlersInstance() {
        return createBundlersInstance(Bundlers.class.getClassLoader());
    }

    /**
     * This convenience method will automatically load a Bundlers instance
     * from either META-INF/services or the default
     * {@link BasicBundlers} if none are found in
     * the services meta-inf.
     *
     * After instantiating the bundlers instance it will load the default
     * bundlers via {@link #loadDefaultBundlers()} as well as requesting
     * the services loader to load any other bundelrs via
     * {@link #loadBundlersFromServices(ClassLoader)}.

     *
     * @param servicesClassLoader the classloader to search for
     *                            META-INF/service registered bundlers
     * @return an instance of Bundlers loaded and configured from the specified ClassLoader
     */
    public static Bundlers createBundlersInstance(ClassLoader servicesClassLoader) {
        ServiceLoader<Bundlers> bundlersLoader = ServiceLoader.load(Bundlers.class, servicesClassLoader);
        Bundlers bundlers = null;
        Iterator<Bundlers> iter = bundlersLoader.iterator();
        if (iter.hasNext()) {
            bundlers = iter.next();
        }
        if (bundlers == null) {
            bundlers = new BasicBundlers();
        }

        bundlers.loadBundlersFromServices(servicesClassLoader);
        return bundlers;
    }

    /**
     * Returns all of the preconfigured, requested, and manually
     * configured bundlers loaded with this instance.
     *
     * @return  a read-only collection of the requested bundlers
     */
    Collection<Bundler> getBundlers();

    /**
     * Returns all of the preconfigured, requested, and manually
     * configured bundlers loaded with this instance that are of
     * a specific BundleType, such as disk images, installers, or
     * remote installers.
     *
     * @return a read-only collection of the requested bundlers
     */
    Collection<Bundler> getBundlers(String type);

    /**
     * A list of the "standard" parameters that bundlers should support
     * or fall back to when their specific parameters are not used.
     *
     * @return an unmodifiable collection of the standard parameters.
     */
    Collection<BundlerParamInfo> getStandardParameters();

    /**
     * Loads the bundlers common to the JDK.  A typical implementation
     * would load:
     * <UL>
     *     <LI>Windows file image</LI>
     *     <LI>Mac .app</LI>
     *     <LI>Linux file image</LI>

     *     <LI>Windows MSI</LI>
     *     <LI>Windows EXE</LI>
     *     <LI>Mac DMG</LI>
     *     <LI>Mac PKG</LI>
     *     <LI>Linux DEB</LI>
     *     <LI>Linux RPM</LI>
     *
     * </UL>
     *
     * This method is called from the {@link #createBundlersInstance(ClassLoader)}
     * and {@link #createBundlersInstance()} methods.
     * NOTE: Because of the module system this method is now not used.
     */
    void loadDefaultBundlers();

    /**
     * Loads bundlers from the META-INF/services directly.
     *
     * This method is called from the {@link #createBundlersInstance(ClassLoader)}
     * and {@link #createBundlersInstance()} methods.
     */
    void loadBundlersFromServices(ClassLoader cl);

    /**
     * Loads a specific bundler into the set of bundlers.
     * Useful for a manually configured bundler.
     *
     * @param bundler the specific bundler to add
     */
    void loadBundler(Bundler bundler);
}
