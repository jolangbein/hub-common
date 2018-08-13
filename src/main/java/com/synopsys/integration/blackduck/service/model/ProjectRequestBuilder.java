/**
 * hub-common
 *
 * Copyright (C) 2018 Black Duck Software, Inc.
 * http://www.blackducksoftware.com/
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.synopsys.integration.blackduck.service.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

import com.synopsys.integration.blackduck.api.generated.component.ProjectRequest;
import com.synopsys.integration.blackduck.api.generated.component.ProjectVersionRequest;
import com.synopsys.integration.blackduck.api.generated.enumeration.ProjectVersionDistributionType;
import com.synopsys.integration.blackduck.api.generated.enumeration.ProjectVersionPhaseType;
import com.synopsys.integration.blackduck.api.generated.enumeration.ProjectVersionRequestPhaseType;
import com.synopsys.integration.builder.AbstractBuilder;
import com.synopsys.integration.rest.RestConstants;
import com.synopsys.integration.validator.AbstractValidator;

public class ProjectRequestBuilder extends AbstractBuilder<ProjectRequest> {
    private String projectName;
    private String description;
    private Boolean projectLevelAdjustments;
    private String projectOwner;
    private Integer projectTier;
    private String distribution = ProjectVersionDistributionType.EXTERNAL.name();
    private String phase = ProjectVersionPhaseType.DEVELOPMENT.name();
    private String versionName;
    private String versionNickname;
    private String releaseComments;
    private String releasedOn;

    @Override
    public AbstractValidator createValidator() {
        final ProjectRequestValidator validator = new ProjectRequestValidator(this);
        return validator;
    }

    @Override
    public ProjectRequest buildObject() {
        final ProjectVersionDistributionType distributionValue = ProjectVersionDistributionType.valueOf(distribution.toUpperCase());
        final ProjectVersionRequestPhaseType phaseValue = ProjectVersionRequestPhaseType.valueOf(phase.toUpperCase());
        final ProjectVersionRequest projectVersionRequest = new ProjectVersionRequest();
        projectVersionRequest.distribution = distributionValue;
        projectVersionRequest.phase = phaseValue;
        projectVersionRequest.versionName = versionName;
        projectVersionRequest.releaseComments = releaseComments;
        if (StringUtils.isNotBlank(releasedOn)) {
            final SimpleDateFormat sdf = new SimpleDateFormat(RestConstants.JSON_DATE_FORMAT);
            try {
                projectVersionRequest.releasedOn = sdf.parse(releasedOn);
            } catch (final ParseException e) {

            }
        }
        projectVersionRequest.nickname = versionNickname;

        final ProjectRequest projectRequest = new ProjectRequest();
        projectRequest.name = projectName;
        projectRequest.description = description;
        projectRequest.projectLevelAdjustments = projectLevelAdjustments;
        projectRequest.projectOwner = projectOwner;
        projectRequest.projectTier = projectTier;
        projectRequest.versionRequest = projectVersionRequest;
        return projectRequest;
    }

    public void setProjectName(final String projectName) {
        this.projectName = projectName;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setProjectLevelAdjustments(final Boolean projectLevelAdjustments) {
        this.projectLevelAdjustments = projectLevelAdjustments;
    }

    public void setProjectOwner(final String projectOwner) {
        this.projectOwner = projectOwner;
    }

    public void setProjectTier(final Integer projectTier) {
        this.projectTier = projectTier;
    }

    public void setDistribution(final String distribution) {
        this.distribution = distribution;
    }

    public void setDistribution(final ProjectVersionDistributionType distribution) {
        this.distribution = distribution.name();
    }

    public void setPhase(final String phase) {
        this.phase = phase;
    }

    public void setPhase(final ProjectVersionPhaseType phase) {
        this.phase = phase.name();
    }

    public void setVersionName(final String versionName) {
        this.versionName = versionName;
    }

    public void setVersionNickname(final String versionNickname) {
        this.versionNickname = versionNickname;
    }

    public void setReleaseComments(final String releaseComments) {
        this.releaseComments = releaseComments;
    }

    public void setReleasedOn(final String releasedOn) {
        this.releasedOn = releasedOn;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getProjectLevelAdjustments() {
        return projectLevelAdjustments;
    }

    public String getProjectOwner() {
        return projectOwner;
    }

    public Integer getProjectTier() {
        return projectTier;
    }

    public String getDistribution() {
        return distribution;
    }

    public String getPhase() {
        return phase;
    }

    public String getVersionName() {
        return versionName;
    }

    public String getVersionNickname() {
        return versionNickname;
    }

    public String getReleaseComments() {
        return releaseComments;
    }

    public String getReleasedOn() {
        return releasedOn;
    }

}
