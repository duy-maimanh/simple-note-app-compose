# Ignore inline messages which lay outside a diff's range of PR
# github.dismiss_out_of_range_messages

# Sometimes it's a README fix, or something like that - which isn't relevant for
# including in a project's CHANGELOG for example
declared_trivial = github.pr_title.include? "#trivial"

# Ensure PR have an assignee
warn "This PR does not have any assignees yet." unless github.pr_json["assignee"]

# Make it more obvious that a PR is a work in progress and shouldn't be merged yet
warn("PR is masked as Work in Progress") if github.pr_title.include? "[WIP]"

# Warn when there is a big PR
warn("Big PR") if git.lines_of_code > 500

# Don't let testing shortcuts get into master by accident
fail("fdescribe left in tests") if `grep -r fdescribe specs/ `.length > 1
fail("fit left in tests") if `grep -r fit specs/ `.length > 1

# To support multi module projects, identify defined modules via the settings.gradle
# Open the file `settings.gradle.kts`
File.open("settings.gradle.kts", "r") do |file_handle|
  # Read through every single file
  file_handle.each_line do |setting|
    # Only check lines which include `include`
    if setting.include? "include"
        # read in the module name
        # Note this goes by the assumption that name equals path
        gradleModule = setting[10, setting.length-13]

        # Process check-style results
        checkstyleFile = String.new(gradleModule + "/build/reports/checkstyle/checkstyle.xml")
        if File.file?(checkstyleFile)
            checkstyle_format.base_path = Dir.pwd
            checkstyle_format.report(checkstyleFile, inline_mode: true)
        end

        # Process Android-Lint results
        androidLintFile = String.new("./" + gradleModule + "/build/reports/lint-results.xml")
        androidLintDebugFile = String.new("./" + gradleModule + "/build/reports/lint-results-debug.xml")
        if File.file?(androidLintFile) || File.file?(androidLintDebugFile)
            android_lint.skip_gradle_task = true # do this if lint was already run in a previous build step
            android_lint.severity = "Warning"
            if File.file?(androidLintFile)
                android_lint.report_file = androidLintFile
            else
                android_lint.report_file = androidLintDebugFile
            end
            android_lint.filtering = true
            android_lint.lint(inline_mode: true)
        end

        # Process Detekt results
        detektFile = String.new("./" + gradleModule + "/build/reports/detekt/detekt.xml")
        if File.file?(detektFile)
            kotlin_detekt.report_file = detektFile
            kotlin_detekt.skip_gradle_task = true
            kotlin_detekt.severity = "warning"
            kotlin_detekt.filtering = true
            kotlin_detekt.detekt(inline_mode: true)
        end
    end
  end
end

warn(File.read('ROOM_CHECK_FAILURE')) if File.exist? 'ROOM_CHECK_FAILURE'