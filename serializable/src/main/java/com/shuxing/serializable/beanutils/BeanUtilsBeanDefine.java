package com.shuxing.serializable.beanutils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;

public class BeanUtilsBeanDefine extends BeanUtilsBean {
	
	public void copyProperties(final Object dest, final Object orig)
			throws IllegalAccessException, InvocationTargetException {

		// Validate existence of the specified beans
		if (dest == null) {
			throw new IllegalArgumentException("No destination bean specified");
		}
		if (orig == null) {
			throw new IllegalArgumentException("No origin bean specified");
		}

		// Copy the properties, converting as necessary
		if (orig instanceof DynaBean) {
			final DynaProperty[] origDescriptors = ((DynaBean) orig)
					.getDynaClass().getDynaProperties();
			for (DynaProperty origDescriptor : origDescriptors) {
				final String name = origDescriptor.getName();
				// Need to check isReadable() for WrapDynaBean
				// (see Jira issue# BEANUTILS-61)
				if (getPropertyUtils().isReadable(orig, name)
						&& getPropertyUtils().isWriteable(dest, name)) {
					final Object value = ((DynaBean) orig).get(name);
					copyProperty(dest, name, value);
				}
			}
		} else if (orig instanceof Map) {
			@SuppressWarnings("unchecked")
			final// Map properties are always of type <String, Object>
			Map<String, Object> propMap = (Map<String, Object>) orig;
			for (final Map.Entry<String, Object> entry : propMap.entrySet()) {
				final String name = entry.getKey();
				if (getPropertyUtils().isWriteable(dest, name)) {
					copyProperty(dest, name, entry.getValue());
				}
			}
		} else /* if (orig is a standard JavaBean) */{
			final PropertyDescriptor[] origDescriptors = getPropertyUtils()
					.getPropertyDescriptors(orig);
			for (PropertyDescriptor origDescriptor : origDescriptors) {
				final String name = origDescriptor.getName();
				if ("class".equals(name)) {
					continue; // No point in trying to set an object's class
				}
				if (getPropertyUtils().isReadable(orig, name)
						&& getPropertyUtils().isWriteable(dest, name)) {
					try {
						final Object value = getPropertyUtils()
								.getSimpleProperty(orig, name);
						// 
						if (value == null) {
							continue;
						}
						copyProperty(dest, name, value);
					} catch (final NoSuchMethodException e) {
						// Should not happen
					}
				}
			}
		}

	}

}
