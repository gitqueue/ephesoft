/********************************************************************************* 
* Ephesoft is a Intelligent Document Capture and Mailroom Automation program 
* developed by Ephesoft, Inc. Copyright (C) 2010-2012 Ephesoft Inc. 
* 
* This program is free software; you can redistribute it and/or modify it under 
* the terms of the GNU Affero General Public License version 3 as published by the 
* Free Software Foundation with the addition of the following permission added 
* to Section 15 as permitted in Section 7(a): FOR ANY PART OF THE COVERED WORK 
* IN WHICH THE COPYRIGHT IS OWNED BY EPHESOFT, EPHESOFT DISCLAIMS THE WARRANTY 
* OF NON INFRINGEMENT OF THIRD PARTY RIGHTS. 
* 
* This program is distributed in the hope that it will be useful, but WITHOUT 
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
* FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more 
* details. 
* 
* You should have received a copy of the GNU Affero General Public License along with 
* this program; if not, see http://www.gnu.org/licenses or write to the Free 
* Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 
* 02110-1301 USA. 
* 
* You can contact Ephesoft, Inc. headquarters at 111 Academy Way, 
* Irvine, CA 92617, USA. or at email address info@ephesoft.com. 
* 
* The interactive user interfaces in modified source and object code versions 
* of this program must display Appropriate Legal Notices, as required under 
* Section 5 of the GNU Affero General Public License version 3. 
* 
* In accordance with Section 7(b) of the GNU Affero General Public License version 3, 
* these Appropriate Legal Notices must retain the display of the "Ephesoft" logo. 
* If the display of the logo is not reasonably feasible for 
* technical reasons, the Appropriate Legal Notices must display the words 
* "Powered by Ephesoft". 
********************************************************************************/ 

package com.ephesoft.dcma.gwt.customworkflow.client.presenter;

import com.ephesoft.dcma.gwt.core.client.EphesoftAsyncCallback;
import com.ephesoft.dcma.gwt.core.client.i18n.LocaleDictionary;
import com.ephesoft.dcma.gwt.core.client.ui.ScreenMaskUtility;
import com.ephesoft.dcma.gwt.core.shared.ConfirmationDialogUtil;
import com.ephesoft.dcma.gwt.customworkflow.client.CustomWorkflowController;
import com.ephesoft.dcma.gwt.customworkflow.client.i18n.CustomWorkflowConstants;
import com.ephesoft.dcma.gwt.customworkflow.client.i18n.CustomWorkflowMessages;
import com.ephesoft.dcma.gwt.customworkflow.client.view.ImportPluginView;
import com.google.gwt.event.shared.HandlerManager;

/**
 * 
 * @author Ephesoft
 *
 */
/**
 * Presenter for Importing a batch class.
 */
public class ImportPluginPresenter extends AbstractCustomWorkflowPresenter<ImportPluginView> {

	public ImportPluginPresenter(final CustomWorkflowController controller, final ImportPluginView view) {
		super(controller, view);
	}

	@Override
	public void bind() {
		/**
		 * Bind your view with the values here.
		 */
	}

	public void showPluginImportView() {
		view.getDialogBox().setWidth(CustomWorkflowConstants._100);
		view.getDialogBox().add(view);
		view.getDialogBox().center();
		view.getDialogBox().show();
		view.getDialogBox().setText(LocaleDictionary.get().getConstantValue(CustomWorkflowConstants.ADD_NEW_PLUGIN));
	}

	@Override
	public void injectEvents(final HandlerManager eventBus) {
		// Event handling to be done here.
	}

	public void onSubmitComplete(boolean isPluginAdded) {
		view.getDialogBox().hide();
		if (isPluginAdded) {
			ConfirmationDialogUtil.showConfirmationDialogSuccess(LocaleDictionary.get().getMessageValue(
					CustomWorkflowMessages.PLUGIN_ADDED_SUCCESSFULLY));
			getController().getCustomWorkflowManagementPresenter().getCustomWorkflowEntryPresenter().getViewAndAddPluginsPresenter()
					.bind();

			getController().getCustomWorkflowManagementPresenter().init();
		}

		ScreenMaskUtility.unmaskScreen();
	}

	public void onSaveClicked(final String pluginName, final String xmlSourcePath, final String jarSourcePath) {

		controller.getRpcService().addNewPlugin(pluginName, xmlSourcePath, jarSourcePath, new EphesoftAsyncCallback<Boolean>() {

			@Override
			public void onSuccess(Boolean isPluginAdded) {

				onSubmitComplete(isPluginAdded);
			}

			@Override
			public void customFailure(Throwable message) {

				ConfirmationDialogUtil.showConfirmationDialogError(LocaleDictionary.get().getMessageValue(
						CustomWorkflowMessages.ERROR_ADDING_NEW_PLUGIN)
						+ " : " + message.getMessage());
				view.getDialogBox().hide();
				ScreenMaskUtility.unmaskScreen();
			}
		});

	}

}
