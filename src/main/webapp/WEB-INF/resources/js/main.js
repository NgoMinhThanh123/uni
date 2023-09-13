

function deleteStudent(path) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Hệ thống có lỗi! Vui lòng quay lại sau!");
        });
    }
} 
function deleteUser(path) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Hệ thống có lỗi! Vui lòng quay lại sau!");
        });
    }
} 

function deleteScoreColumn(path, id) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Hệ thống có lỗi! Vui lòng quay lại sau!");
        });
    }
} 

function deleteFaculty(path) {
    if (confirm("Bạn chắc chắn xóa không?") === true) {
        fetch(path, {
            method: "delete"
        }).then(res => {
            if (res.status === 204)
                location.reload();
            else
                alert("Hệ thống có lỗi! Vui lòng quay lại sau!");
        });
    }
} 

