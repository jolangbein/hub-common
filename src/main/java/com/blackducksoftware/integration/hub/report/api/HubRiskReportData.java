package com.blackducksoftware.integration.hub.report.api;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import com.blackducksoftware.integration.util.XStreamHelper;

public class HubRiskReportData {
    private VersionReport report;

    private int totalBomEntries;

    private int vulnerabilityRiskHighCount;

    private int vulnerabilityRiskMediumCount;

    private int vulnerabilityRiskLowCount;

    private int vulnerabilityRiskNoneCount;

    private int licenseRiskHighCount;

    private int licenseRiskMediumCount;

    private int licenseRiskLowCount;

    private int licenseRiskNoneCount;

    private int operationalRiskHighCount;

    private int operationalRiskMediumCount;

    private int operationalRiskLowCount;

    private int operationalRiskNoneCount;

    public void setReport(VersionReport report) {
        this.report = report;

        vulnerabilityRiskHighCount = 0;
        vulnerabilityRiskMediumCount = 0;
        vulnerabilityRiskLowCount = 0;

        licenseRiskHighCount = 0;
        licenseRiskMediumCount = 0;
        licenseRiskLowCount = 0;

        operationalRiskHighCount = 0;
        operationalRiskMediumCount = 0;
        operationalRiskLowCount = 0;

        List<AggregateBomViewEntry> bomEntries = report.getAggregateBomViewEntries();
        for (AggregateBomViewEntry bomEntry : bomEntries) {
            if (bomEntry.getVulnerabilityRisk().getHIGH() > 0) {
                vulnerabilityRiskHighCount++;
            } else if (bomEntry.getVulnerabilityRisk().getMEDIUM() > 0) {
                vulnerabilityRiskMediumCount++;
            } else if (bomEntry.getVulnerabilityRisk().getLOW() > 0) {
                vulnerabilityRiskLowCount++;
            }

            if (bomEntry.getLicenseRisk().getHIGH() > 0) {
                licenseRiskHighCount++;
            } else if (bomEntry.getLicenseRisk().getMEDIUM() > 0) {
                licenseRiskMediumCount++;
            } else if (bomEntry.getLicenseRisk().getLOW() > 0) {
                licenseRiskLowCount += 1;
            }

            if (bomEntry.getOperationalRisk().getHIGH() > 0) {
                operationalRiskHighCount++;
            } else if (bomEntry.getOperationalRisk().getMEDIUM() > 0) {
                operationalRiskMediumCount++;
            } else if (bomEntry.getOperationalRisk().getLOW() > 0) {
                operationalRiskLowCount++;
            }
        }

        totalBomEntries = bomEntries.size();

        vulnerabilityRiskNoneCount = totalBomEntries - vulnerabilityRiskHighCount - vulnerabilityRiskMediumCount - vulnerabilityRiskLowCount;
        licenseRiskNoneCount = totalBomEntries - licenseRiskHighCount - licenseRiskMediumCount - licenseRiskLowCount;
        operationalRiskNoneCount = totalBomEntries - operationalRiskHighCount - operationalRiskMediumCount - operationalRiskLowCount;
    }

    public void readFromInputStream(InputStream inputStream) {
        XStreamHelper<HubRiskReportData> xStreamHelper = new XStreamHelper<HubRiskReportData>();
        HubRiskReportData that = xStreamHelper.fromXML(inputStream);

        licenseRiskHighCount = that.licenseRiskHighCount;
        licenseRiskMediumCount = that.licenseRiskMediumCount;
        licenseRiskLowCount = that.licenseRiskLowCount;
        licenseRiskNoneCount = that.licenseRiskNoneCount;
        vulnerabilityRiskHighCount = that.vulnerabilityRiskHighCount;
        vulnerabilityRiskMediumCount = that.vulnerabilityRiskMediumCount;
        vulnerabilityRiskLowCount = that.vulnerabilityRiskLowCount;
        vulnerabilityRiskNoneCount = that.vulnerabilityRiskNoneCount;
        operationalRiskHighCount = that.operationalRiskHighCount;
        operationalRiskMediumCount = that.operationalRiskMediumCount;
        operationalRiskLowCount = that.operationalRiskLowCount;
        operationalRiskNoneCount = that.operationalRiskNoneCount;
        report = that.report;
    }

    public void writeToOutputStream(OutputStream outputStream) {
        XStreamHelper<HubRiskReportData> xStreamHelper = new XStreamHelper<HubRiskReportData>();
        xStreamHelper.toXML(this, outputStream);
    }

    public double getPercentage(double count) {
        double totalCount = totalBomEntries;
        double percentage = 0;
        if (totalCount > 0 && count > 0) {
            percentage = (count / totalCount) * 100;
        }
        return percentage;
    }

    public String htmlEscape(String valueToEscape) {
        if (StringUtils.isBlank(valueToEscape)) {
            return null;
        }
        return StringEscapeUtils.escapeHtml4(valueToEscape);
    }

    public List<AggregateBomViewEntry> getBomEntries() {
        return report.getAggregateBomViewEntries();
    }

    public VersionReport getReport() {
        return report;
    }

    public int getVulnerabilityRiskHighCount() {
        return vulnerabilityRiskHighCount;
    }

    public int getVulnerabilityRiskMediumCount() {
        return vulnerabilityRiskMediumCount;
    }

    public int getVulnerabilityRiskLowCount() {
        return vulnerabilityRiskLowCount;
    }

    public int getVulnerabilityRiskNoneCount() {
        return vulnerabilityRiskNoneCount;
    }

    public int getLicenseRiskHighCount() {
        return licenseRiskHighCount;
    }

    public int getLicenseRiskMediumCount() {
        return licenseRiskMediumCount;
    }

    public int getLicenseRiskLowCount() {
        return licenseRiskLowCount;
    }

    public int getLicenseRiskNoneCount() {
        return licenseRiskNoneCount;
    }

    public int getOperationalRiskHighCount() {
        return operationalRiskHighCount;
    }

    public int getOperationalRiskMediumCount() {
        return operationalRiskMediumCount;
    }

    public int getOperationalRiskLowCount() {
        return operationalRiskLowCount;
    }

    public int getOperationalRiskNoneCount() {
        return operationalRiskNoneCount;
    }

}
