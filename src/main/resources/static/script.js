// Function to show/hide sections
function showSection(section) {
    document.getElementById("add-section").style.display = "none";
    document.getElementById("search-section").style.display = "none";
    document.getElementById("jobs-section").style.display = "none";  // This should be set to "block" when fetching jobs

    document.getElementById(`${section}-section`).style.display = "block";
}

// Fetching all jobs and displaying them
async function fetchJobs() {
  //  console.log("Fetching jobs..."); // Debugging line to check if this is being called
    showSection('jobs'); // Make sure the "View Jobs" section is displayed
    try {
        const response = await fetch("https://job-post-platform-with-springboot-rxrb.onrender.com/posts"); // Update the backend API endpoint if needed
        const jobs = await response.json();
    //    console.log("Fetched jobs:", jobs);  // This should log the fetched jobs in the console

        if (!jobs || jobs.length === 0) {
            showPopupMessage("No jobs available.");
            return;
        }

        const jobList = document.getElementById("jobList");
        jobList.innerHTML = "";  // Clear previous results

        jobs.forEach(job => {
            jobList.innerHTML += `
                <tr>
                    <td>${job.profile}</td>
                    <td>${job.des}</td>
                    <td>${job.exp}</td>
                    <td>${job.tech.join(", ")}</td>
                    <td>
                        <button class="btn btn-warning btn-sm" onclick="editJob(${job.id})">Edit</button>
                        <button class="btn btn-danger btn-sm" onclick="deleteJob(${job.id})">Delete</button>
                    </td>
                </tr>
            `;
        });
    } catch (error) {
        console.error("Error fetching jobs:", error);
        showPopupMessage("Error fetching jobs.");
    }
}

// Function to show a popup message
function showPopupMessage(message) {
    const popup = document.createElement("div");
    popup.classList.add("popup-message");
    popup.textContent = message;
    document.body.appendChild(popup);
    setTimeout(() => {
        popup.remove();
    }, 3000); // Hide message after 3 seconds
}

// Add a new job
async function addJob() {
    const profile = document.getElementById("profile").value;
    const des = document.getElementById("description").value;
    const exp = document.getElementById("experience").value;
    const tech = document.getElementById("technologies").value.split(",");

    if (profile && des && exp && tech.length > 0) {
        const job = { profile, des, exp, tech };
        try {
            await fetch("https://job-post-platform-with-springboot-rxrb.onrender.com/addJobPost", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(job)
            });
            fetchJobs(); // Refresh the job list
            showPopupMessage("Job added successfully!");
        } catch (error) {
            console.error("Error adding job:", error);
        }
    }
}

// Function to search jobs
async function searchJobs() {
    const searchTerm = document.getElementById("search").value.trim();

    if (searchTerm.length === 0) {
        document.getElementById("searchResults").innerHTML = ""; // Clear results if empty
        return;
    }

    try {
        const response = await fetch(`https://job-post-platform-with-springboot-rxrb.onrender.com/posts/search/${encodeURIComponent(searchTerm)}`);
        const jobs = await response.json();

        displaySearchResults(jobs);
    } catch (error) {
        console.error("Error searching jobs:", error);
    }
}

// Display search results
function displaySearchResults(jobs) {
    const searchResults = document.getElementById("searchResults");
    searchResults.innerHTML = ""; // Clear previous results

    if (jobs.length === 0) {
        searchResults.innerHTML = "<tr><td colspan='5'>No matching jobs found</td></tr>";
        return;
    }

    jobs.forEach(job => {
        searchResults.innerHTML += `
            <tr>
                <td>${job.profile}</td>
                <td>${job.des}</td>
                <td>${job.exp}</td>
                <td>${Array.isArray(job.tech) ? job.tech.join(", ") : job.tech}</td>
            </tr>
        `;
    });
}

function editJob(jobId) {
    console.log("Editing job with ID:", jobId);
    // Implement edit functionality here
}

function deleteJob(jobId) {
    console.log("Deleting job with ID:", jobId);
    // Implement delete functionality here
}
