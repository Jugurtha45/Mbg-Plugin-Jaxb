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

package org.mybatis.generator.hierarchical.plugins.jaxbAnnotations;


import hierarchical.fieldType.withLobs.modelDto.*; //these are our mbg generated compiled model classes that we want to test.

import org.mybatis.generator.JaxbAnnotationsTestUtils;

import java.lang.annotation.Annotation;

import java.lang.reflect.Field;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlTransient;

import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;

import org.junit.runners.JUnit4;


/**
This class is used to test the MyBatis Generator generated model classes for
the presence of Jaxb annotations when the Xml access type specified is FIELD
and LOBs fields are marshaled.

@author Mahiar Mody
*/
@RunWith(JUnit4.class)
public class FieldTypePresenceWithLobsTest
{
	@Test
	public void testXmlRootElementPresence()
	{
		JaxbAnnotationsTestUtils.checkXmlRootElementAnnotation(UsersKey.class, "usERs", null);
		JaxbAnnotationsTestUtils.checkXmlRootElementAnnotation(Users.class, "usERs", null);

		JaxbAnnotationsTestUtils.checkXmlRootElementAnnotation(UserSkillsKey.class, "userSkillsKey", null);
		JaxbAnnotationsTestUtils.checkXmlRootElementAnnotation(UserSkills.class, "userSkills", null);

		JaxbAnnotationsTestUtils.checkXmlRootElementAnnotation(UsersToSkillsKey.class, "usersToSkillsKey", null);

		JaxbAnnotationsTestUtils.checkXmlRootElementAnnotation(UserPhotos.class, "Photos", "http://mybatis.generator.org/plugins/jaxb/test");
		JaxbAnnotationsTestUtils.checkXmlRootElementAnnotation(UserPhotosWithBLOBs.class, "Photos", "http://mybatis.generator.org/plugins/jaxb/test");

		JaxbAnnotationsTestUtils.checkXmlRootElementAnnotation(UserBlog.class, "userBlog", null);
		JaxbAnnotationsTestUtils.checkXmlRootElementAnnotation(UserBlogWithBLOBs.class, "userBlogWithBLOBs", null);

		JaxbAnnotationsTestUtils.checkXmlRootElementAnnotation(UserTutorialKey.class, "Tutorial", null);
		JaxbAnnotationsTestUtils.checkXmlRootElementAnnotation(UserTutorial.class, "Tutorial", null);
		JaxbAnnotationsTestUtils.checkXmlRootElementAnnotation(UserTutorialWithBLOBs.class, "Tutorial", null);


		JaxbAnnotationsTestUtils.checkAccessTypeFieldClassAnnotation(UsersKey.class);
		JaxbAnnotationsTestUtils.checkAccessTypeFieldClassAnnotation(Users.class);
		JaxbAnnotationsTestUtils.checkAccessTypeFieldClassAnnotation(UserSkillsKey.class);
		JaxbAnnotationsTestUtils.checkAccessTypeFieldClassAnnotation(UserSkills.class);
		JaxbAnnotationsTestUtils.checkAccessTypeFieldClassAnnotation(UsersToSkillsKey.class);
		JaxbAnnotationsTestUtils.checkAccessTypeFieldClassAnnotation(UserPhotos.class);
		JaxbAnnotationsTestUtils.checkAccessTypeFieldClassAnnotation(UserPhotosWithBLOBs.class);
		JaxbAnnotationsTestUtils.checkAccessTypeFieldClassAnnotation(UserBlog.class);
		JaxbAnnotationsTestUtils.checkAccessTypeFieldClassAnnotation(UserBlogWithBLOBs.class);
		JaxbAnnotationsTestUtils.checkAccessTypeFieldClassAnnotation(UserTutorialKey.class);
		JaxbAnnotationsTestUtils.checkAccessTypeFieldClassAnnotation(UserTutorial.class);
		JaxbAnnotationsTestUtils.checkAccessTypeFieldClassAnnotation(UserTutorialWithBLOBs.class);
	}



	@Test
	public void testXmlAccessorOrderPresence()
	{
		Class<?> cls = Users.class;

		Annotation annt = cls.getAnnotation(XmlAccessorOrder.class);
		Assert.assertNotNull("@XmlAccessorOrder annotation missing from Users class", annt);

		XmlAccessOrder value = ((XmlAccessorOrder) annt).value();
		Assert.assertTrue("XmlAccessOrder attribute of XmlaccessorOrder element is wrong in Users class",
			XmlAccessOrder.ALPHABETICAL.equals(value));



		cls = UsersKey.class;

		annt = cls.getAnnotation(XmlAccessorOrder.class);
		Assert.assertNotNull("@XmlAccessorOrder annotation missing from UsersKey class", annt);

		value = ((XmlAccessorOrder) annt).value();
		Assert.assertTrue("XmlAccessOrder attribute of XmlaccessorOrder element is wrong in UsersKey class",
			XmlAccessOrder.ALPHABETICAL.equals(value));
	}



	@Test
	public void testFieldLevelExplicitXmlAttributeAnnotations()
	{
		Field fldUserId = null, fldFirstName=null;

		try
		{
			fldUserId = UsersKey.class.getDeclaredField("userId");
			fldFirstName = Users.class.getDeclaredField("firstName");
		}
		catch(NoSuchFieldException e)
		{
			Assert.fail("NoSuchFieldException thrown. Check MyBatisGeneratorConfig.xml: " + e.getMessage());
		}

		fldUserId.setAccessible(true);
		fldFirstName.setAccessible(true);

		Annotation annt = fldUserId.getAnnotation(XmlAttribute.class);
		Assert.assertNotNull("@XmlAttribute annotation missing from UsersKey.userId java class field", annt);


		annt = fldFirstName.getAnnotation(XmlAttribute.class);
		Assert.assertNotNull("@XmlAttribute annotation missing from Users.firstName java class field", annt);

		String name = ((XmlAttribute) annt).name();
		boolean required = ((XmlAttribute) annt).required();

		Assert.assertEquals("name attribute of the @XmlAttribute annotation is wrong or missing in Users.firstName java class field",
			"first_name", name);

		Assert.assertTrue("required attribute of @XmlAttribute annotation is wrong or missing in Users.firstName java class field",
			required);
	}



	@Test
	public void testFieldLevelImplicitXmlTransientAnnotationsNotPresent()
	{
		Field fldPhoto = null, fldBlogTxt=null;

		try
		{
			fldPhoto = UserPhotosWithBLOBs.class.getDeclaredField("photo");
			fldBlogTxt = UserBlogWithBLOBs.class.getDeclaredField("blogText");
		}
		catch(NoSuchFieldException e)
		{
			Assert.fail("NoSuchFieldException thrown. Check SetupDbTestScripts.sql file: " + e.getMessage());
		}

		fldPhoto.setAccessible(true);
		fldBlogTxt.setAccessible(true);

		Annotation annt = fldPhoto.getAnnotation(XmlTransient.class);
		Assert.assertNull("@XmlTransient annotation should not be present on UserPhotosWithBLOBs.photo java class field", annt);


		annt = fldBlogTxt.getAnnotation(XmlTransient.class);
		Assert.assertNull("@XmlTransient annotation should not be present on UserBlogWithBLOBs.blogText java class field", annt);
	}
}
