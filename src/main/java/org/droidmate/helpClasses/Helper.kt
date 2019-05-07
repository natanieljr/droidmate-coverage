// DroidMate, an automated execution generator for Android apps.
// Copyright (C) 2012-2018. Saarland University
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
//
// Current Maintainers:
// Nataniel Borges Jr. <nataniel dot borges at cispa dot saarland>
// Jenny Hotzkow <jenny dot hotzkow at cispa dot saarland>
//
// Former Maintainers:
// Konrad Jamrozik <jamrozik at st dot cs dot uni-saarland dot de>
//
// web: www.droidmate.org

package org.droidmate.helpClasses

import org.xmlpull.v1.XmlPullParserException
import soot.jimple.infoflow.android.manifest.ProcessManifest

import java.io.IOException

/**
 * Originally copied to a large extent from the aggregator project.
 *
 * @author Original code by Manuel Benz (https://github.com/mbenz89)
 */
object Helper {

    private var permissions: Set<String>? = null
    private var targetApi: Int = -1

    fun initializeManifestInfo(apkPath: String) {
        var processMan: ProcessManifest? = null
        try {
            processMan = ProcessManifest(apkPath)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        }

        permissions = processMan!!.permissions
        targetApi = processMan.targetSdkVersion()
    }

    fun hasPermission(permission: String): Boolean {
        return permissions!!.contains(permission)
    }

    fun getTargetApi(): Int = targetApi
}
