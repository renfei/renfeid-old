/**
 * 存储localStorage
 */
function setLocalStore(name, content) {
    if (!name) return;
    if (typeof content !== 'string') {
        content = JSON.stringify(content);
    }
    window.localStorage.setItem(name, content);
}

/**
 * 获取localStorage
 */
function getLocalStore(name) {
    if (!name) return;
    return window.localStorage.getItem(name);
}

/**
 * 删除localStorage
 */
function removeLocalStore(name) {
    if (!name) return;
    window.localStorage.removeItem(name);
}

function setSessionStore(name, content) {
    if (!name) return;
    if (typeof content !== 'string') {
        content = JSON.stringify(content);
    }
    window.sessionStorage.setItem(name, content);
}

function getSessionStore(name) {
    if (!name) return;
    return window.sessionStorage.getItem(name);
}

function removeSessionStore(name) {
    if (!name) return;
    window.sessionStorage.removeItem(name);
}