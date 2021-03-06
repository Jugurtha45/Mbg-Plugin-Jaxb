/*
 *    Copyright 2014 Mahiar Mody.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */


package org.mybatis.generator.conditional.plugins.jaxbAnnotations;


import conditional.propertyType.noLobs.modelDto.*; //these are our mbg generated compiled model classes that we want to test.

import org.mybatis.generator.JaxbAnnotationsTestUtils;

import java.lang.annotation.Annotation;

import java.lang.reflect.Method;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAttribute;

import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.junit.runners.JUnit4;


/**
This class is used to test the MyBatis Generator generated model classes with
Conditional Model Type, for the presence of Jaxb annotations when the Xml
access type specified is PROPERTY and LOBs fields are not marshaled.

@author Mahiar Mody
*/
@RunWith(JUnit4.class)
public class PropertyTypeConditionalModelNoLobsTest
{
	@Test
	public void testXmlRootElementPresence()
	{
		JaxbAnnotationsTestUtils.checkXmlRootElementAnnotation(UserTutorial.class, "Tutorial", null);
	}


	@Test
	public void testXmlAccessTypeAnnotationPresent()
	{
		JaxbAnnotationsTestUtils.checkAccessTypePropertyClassAnnotation(UserTutorial.class);
	}



	@Test
	public void testPrimaryKeyMethodXmlAttributeNotInKeyClass()
	{
		Method getUserTutorialId = null;

		Class<UserTutorial> cls = UserTutorial.class;

		try
		{
			getUserTutorialId = cls.getDeclaredMethod("getUserTutorialId");
		}
		catch(NoSuchMethodException e)
		{
			Assert.fail("NoSuchMethodException thrown. Check MyBatisGeneratorConfig.xml: " + e.getMessage());
		}

		//getUserTutorialId.setAccessible(true);


		Annotation annt = getUserTutorialId.getAnnotation(XmlAttribute.class);
		Assert.assertNotNull("@XmlAttribute annotation missing from UserTutorial.getUserTutorialId() method", annt);

		String name = ((XmlAttribute) annt).name();
		boolean required = ((XmlAttribute) annt).required();

		Assert.assertEquals("name attribute of the @XmlAttribute annotation is wrong or missing in UserTutorial.getUserTutorialId() method",
			"tutorialId", name);

		Assert.assertTrue("required attribute of @XmlAttribute annotation is wrong or missing in UserTutorial.getUserTutorialId() method",
			required);
	}



	@Test
	public void testMethodInBaseClassIsAnnotated()
	{
		Method getUserId = null;

		try
		{
			getUserId = UserTutorial.class.getDeclaredMethod("getUserId");
		}
		catch(NoSuchMethodException e)
		{
			Assert.fail("NoSuchMethodException thrown. Check SetupDbTestScripts.sql file: " + e.getMessage());
		}

		//getUserId.setAccessible(true);

		Annotation annt = getUserId.getAnnotation(XmlElement.class);
		Assert.assertNotNull("@XmlElement annotation missing from UserTutorial.getUserId() method", annt);


		String name = ((XmlElement) annt).name();
		boolean required = ((XmlElement) annt).required();

		Assert.assertEquals("name attribute of the @XmlElement annotation is wrong or missing in UserTutorial.getUserId() method",
			"userId", name);

		Assert.assertTrue("required attribute of @XmlElement annotation is wrong or missing in UserTutorial.getUserId() method",
			required);
	}



	@Test
	public void testGetNarrativeMethodNotAnnotatedInBlobsClass()
	{
		Method getNarrative = null;

		try
		{
			getNarrative = UserTutorialWithBLOBs.class.getDeclaredMethod("getNarrative");
		}
		catch(NoSuchMethodException e)
		{
			Assert.fail("NoSuchMethodException thrown. Check SetupDbTestScripts.sql file: " + e.getMessage());
		}

		//getNarrative.setAccessible(true);

		Annotation[] arrAnnts = getNarrative.getDeclaredAnnotations();
		Assert.assertTrue("UserTutorialWithBLOBs.getNarrative() method should NOT be annotated, but it is.", arrAnnts.length==0);
	}



	@Test
	public void testClassWithBlobsNotAnnotated()
	{
		Annotation[] arrAnnts = UserTutorialWithBLOBs.class.getDeclaredAnnotations();
		Assert.assertTrue("UserTutorialWithBLOBs class should NOT be annotated at all, but it is.", arrAnnts.length == 0);
	}
}
